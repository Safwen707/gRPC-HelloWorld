package com.grpc.hello.server;

import com.grpc.hello.HelloRequest;
import com.grpc.hello.HelloResponse;
import com.grpc.hello.MultilingualGreeterGrpc;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Implementation of the MultilingualGreeter service
 */
public class MultilingualGreeterImpl extends MultilingualGreeterGrpc.MultilingualGreeterImplBase {
    private static final Logger logger = Logger.getLogger(MultilingualGreeterImpl.class.getName());

    // Map of greeting templates for different languages
    private static final Map<String, String> GREETING_TEMPLATES = new HashMap<>();
    static {
        // English greeting
        GREETING_TEMPLATES.put("en", "Hello, %s %s!");
        // French greeting
        GREETING_TEMPLATES.put("fr", "Bonjour, %s %s!");
        // Arabic greeting
        GREETING_TEMPLATES.put("ar", "مرحبا، %s %s!");
    }

    /**
     * Generate a greeting based on the language code
     */
    private String generateGreeting(HelloRequest request) {
        String template = GREETING_TEMPLATES.getOrDefault(
                request.getLanguageCode(), 
                GREETING_TEMPLATES.get("en")); // Default to English if language not supported
        
        return String.format(template, request.getFirstName(), request.getLastName());
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Received unary request from: " + request.getFirstName() + " " + request.getLastName());
        
        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(generateGreeting(request))
                .build();
                
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sayHellosServerStreaming(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Received server streaming request from: " + request.getFirstName());
        
        // Generate base greeting
        String baseGreeting = generateGreeting(request);
        
        try {
            // Send multiple responses with slight variations
            responseObserver.onNext(HelloResponse.newBuilder()
                    .setGreeting(baseGreeting)
                    .build());
            TimeUnit.MILLISECONDS.sleep(300);
            
            responseObserver.onNext(HelloResponse.newBuilder()
                    .setGreeting("Welcome! " + baseGreeting)
                    .build());
            TimeUnit.MILLISECONDS.sleep(300);
            
            responseObserver.onNext(HelloResponse.newBuilder()
                    .setGreeting("Nice to meet you! " + baseGreeting)
                    .build());
            TimeUnit.MILLISECONDS.sleep(300);
            
            responseObserver.onNext(HelloResponse.newBuilder()
                    .setGreeting("Have a great day! " + baseGreeting)
                    .build());
            
            responseObserver.onCompleted();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StreamObserver<HelloRequest> sayHellosClientStreaming(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            private final StringBuilder namesBuilder = new StringBuilder();
            private String lastLanguageCode = "en"; // Default to English

            @Override
            public void onNext(HelloRequest request) {
                logger.info("Received client streaming request part from: " + request.getFirstName());
                
                if (namesBuilder.length() > 0) {
                    namesBuilder.append(", ");
                }
                namesBuilder.append(request.getFirstName()).append(" ").append(request.getLastName());
                
                // Save the last language code received for the final response
                lastLanguageCode = request.getLanguageCode();
            }

            @Override
            public void onError(Throwable t) {
                logger.warning("Client streaming error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Generate greeting for multiple people
                String template = GREETING_TEMPLATES.getOrDefault(lastLanguageCode, GREETING_TEMPLATES.get("en"));
                String greeting = "Group greeting: " + String.format(template.split(",")[0] + " to all: %s!", namesBuilder.toString());
                
                responseObserver.onNext(HelloResponse.newBuilder().setGreeting(greeting).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest> sayHellosBidirectional(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest request) {
                logger.info("Received bidirectional request from: " + request.getFirstName());
                
                // For each request received, immediately send back a response
                String greeting = generateGreeting(request);
                responseObserver.onNext(HelloResponse.newBuilder().setGreeting(greeting).build());
            }

            @Override
            public void onError(Throwable t) {
                logger.warning("Bidirectional streaming error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // When client signals it's done, send final message and complete
                responseObserver.onNext(
                    HelloResponse.newBuilder()
                        .setGreeting("Bidirectional streaming completed. Goodbye!")
                        .build()
                );
                responseObserver.onCompleted();
            }
        };
    }
}