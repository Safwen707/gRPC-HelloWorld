package com.grpc.hello.client;

import com.grpc.hello.HelloRequest;
import com.grpc.hello.HelloResponse;
import com.grpc.hello.MultilingualGreeterGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A client that requests greetings from the {@code MultilingualGreeter} server.
 */
public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final MultilingualGreeterGrpc.MultilingualGreeterBlockingStub blockingStub;
    private final MultilingualGreeterGrpc.MultilingualGreeterStub asyncStub;

    /** Construct client for accessing server using the existing channel. */
    public HelloWorldClient(Channel channel) {
        blockingStub = MultilingualGreeterGrpc.newBlockingStub(channel);
        asyncStub = MultilingualGreeterGrpc.newStub(channel);
    }

    /**
     * Simple unary call to greet someone in a specific language
     */
    public void greetPerson(String firstName, String lastName, String languageCode) {
        logger.info("Will try to greet " + firstName + " " + lastName + " in " + languageCode);
        
        HelloRequest request = HelloRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLanguageCode(languageCode)
                .build();
        
        HelloResponse response;
        try {
            response = blockingStub.sayHello(request);
            logger.info("Greeting: " + response.getGreeting());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }

    /**
     * Server-streaming example. Obtain multiple greeting messages from the server.
     */
    public void serverStreamingGreet(String firstName, String lastName, String languageCode) {
        logger.info("Server streaming example with " + firstName + " " + lastName);
        
        HelloRequest request = HelloRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLanguageCode(languageCode)
                .build();
        
        try {
            // Server streaming call
            blockingStub.sayHellosServerStreaming(request).forEachRemaining(
                response -> logger.info("Streaming response: " + response.getGreeting())
            );
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
    }

    /**
     * Client-streaming example. Sends multiple greetings to the server.
     */
    public void clientStreamingGreet() throws InterruptedException {
        logger.info("Client streaming example");
        final CountDownLatch finishLatch = new CountDownLatch(1);

        // Sample data: people to greet in different languages
        final List<Person> people = Arrays.asList(
                new Person("John", "Doe", "en"),
                new Person("Marie", "Dubois", "fr"),
                new Person("Ahmed", "Hassan", "ar"),
                new Person("Alice", "Smith", "en")
        );
        
        StreamObserver<HelloResponse> responseObserver = new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse response) {
                logger.info("Client streaming response: " + response.getGreeting());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Client streaming failed: {0}", t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Client streaming completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<HelloRequest> requestObserver = asyncStub.sayHellosClientStreaming(responseObserver);
        try {
            // Send all person data to server
            for (Person person : people) {
                HelloRequest request = HelloRequest.newBuilder()
                        .setFirstName(person.firstName)
                        .setLastName(person.lastName)
                        .setLanguageCode(person.languageCode)
                        .build();
                
                requestObserver.onNext(request);
                // Add some delay between requests
                Thread.sleep(200);
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        
        // Mark the end of requests
        requestObserver.onCompleted();

        // Wait for server to respond
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            logger.warning("Client streaming did not complete within 1 minute");
        }
    }

    /**
     * Bidirectional-streaming example. Send and receive multiple greetings.
     */
    public void bidirectionalStreamingGreet() throws InterruptedException {
        logger.info("Bidirectional streaming example");
        final CountDownLatch finishLatch = new CountDownLatch(1);

        // Sample data: people to greet in different languages
        final List<Person> people = Arrays.asList(
                new Person("Carlos", "Rodriguez", "en"),
                new Person("Sophie", "Martin", "fr"),
                new Person("Mustafa", "Ali", "ar")
        );
        
        StreamObserver<HelloResponse> responseObserver = new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse response) {
                logger.info("Bidirectional response: " + response.getGreeting());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Bidirectional streaming failed: {0}", t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Bidirectional streaming completed");
                finishLatch.countDown();
            }
        };

        StreamObserver<HelloRequest> requestObserver = asyncStub.sayHellosBidirectional(responseObserver);
        try {
            // Send all person data to server
            for (Person person : people) {
                HelloRequest request = HelloRequest.newBuilder()
                        .setFirstName(person.firstName)
                        .setLastName(person.lastName)
                        .setLanguageCode(person.languageCode)
                        .build();
                
                requestObserver.onNext(request);
                Thread.sleep(300);
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        
        // Mark the end of requests
        requestObserver.onCompleted();

        // Wait for server to respond
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            logger.warning("Bidirectional streaming did not complete within 1 minute");
        }
    }

    /**
     * Demo all the different types of gRPC communication
     */
    public void demonstrateAllCommunicationPatterns() throws InterruptedException {
        System.out.println("\n\n=== Unary RPC Examples ===");
        greetPerson("John", "Smith", "en");  // English
        greetPerson("Pierre", "Dubois", "fr"); // French
        greetPerson("Ahmed", "Hassan", "ar"); // Arabic

        System.out.println("\n\n=== Server Streaming RPC Example ===");
        serverStreamingGreet("Maria", "Garcia", "en");

        System.out.println("\n\n=== Client Streaming RPC Example ===");
        clientStreamingGreet();

        System.out.println("\n\n=== Bidirectional Streaming RPC Example ===");
        bidirectionalStreamingGreet();
    }

    /**
     * Helper class for person data
     */
    private static class Person {
        final String firstName;
        final String lastName;
        final String languageCode;

        Person(String firstName, String lastName, String languageCode) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.languageCode = languageCode;
        }
    }

    /**
     * Main launches the client from the command line.
     */
    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";
        if (args.length > 0) {
            target = args[0];
        }

        // Create a communication channel to the server
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext() // For development only, not secure
                .build();
        
        try {
            HelloWorldClient client = new HelloWorldClient(channel);
            client.demonstrateAllCommunicationPatterns();
        } finally {
            // Shut down the channel
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}