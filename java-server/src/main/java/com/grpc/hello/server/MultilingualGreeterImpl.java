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
    
    // Map of welcome messages for different languages
    private static final Map<String, String[]> WELCOME_MESSAGES = new HashMap<>();
    
    static {
        // English greetings
        GREETING_TEMPLATES.put("en", "Hello, %s %s!");
        WELCOME_MESSAGES.put("en", new String[] {
            "Welcome!",
            "Nice to meet you!",
            "Have a great day!"
        });
        
        // French greetings
        GREETING_TEMPLATES.put("fr", "Bonjour, %s %s!");
        WELCOME_MESSAGES.put("fr", new String[] {
            "Bienvenue!",
            "Ravi de vous rencontrer!",
            "Passez une bonne journée!"
        });
        
        // Arabic greetings
        GREETING_TEMPLATES.put("ar", "مرحبا، %s %s!");
        WELCOME_MESSAGES.put("ar", new String[] {
            "أهلاً وسهلاً!",
            "سعيد بلقائك!",
            "أتمنى لك يوماً رائعاً!"
        });
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
        
        // Get language code
        String langCode = request.getLanguageCode();
        if (!WELCOME_MESSAGES.containsKey(langCode)) {
            langCode = "en"; // Default to English if language not supported
        }
        
        // Generate base greeting
        String baseGreeting = generateGreeting(request);
        String[] welcomeMessages = WELCOME_MESSAGES.get(langCode);
        
        try {
            // Send base greeting
            responseObserver.onNext(HelloResponse.newBuilder()
                    .setGreeting(baseGreeting)
                    .build());
            TimeUnit.MILLISECONDS.sleep(300);
            
            // Créer des messages de réponse personnalisés selon la langue
            String[] responseMessages = new String[welcomeMessages.length];
            for (int i = 0; i < welcomeMessages.length; i++) {
                // Personnaliser les messages selon la langue
                switch(langCode) {
                    case "fr":
                        if (i == 0) {
                            responseMessages[i] = welcomeMessages[i] + " " + baseGreeting.replace("Bonjour", "");
                        } else {
                            responseMessages[i] = welcomeMessages[i] + " " + request.getFirstName() + " " + request.getLastName() + "!";
                        }
                        break;
                    case "ar":
                        if (i == 0) {
                            responseMessages[i] = welcomeMessages[i] + " " + baseGreeting.replace("مرحبا", "");
                        } else {
                            responseMessages[i] = welcomeMessages[i] + " " + request.getFirstName() + " " + request.getLastName() + "!";
                        }
                        break;
                    case "en":
                    default:
                        if (i == 0) {
                            responseMessages[i] = welcomeMessages[i] + " " + baseGreeting.replace("Hello", "");
                        } else {
                            responseMessages[i] = welcomeMessages[i] + " " + request.getFirstName() + " " + request.getLastName() + "!";
                        }
                        break;
                }
            }
            
            // Envoyer les messages personnalisés
            for (String message : responseMessages) {
                responseObserver.onNext(HelloResponse.newBuilder()
                        .setGreeting(message)
                        .build());
                TimeUnit.MILLISECONDS.sleep(300);
            }
            
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
                // Create appropriate group greeting based on language
                String greeting;
                
                // Generate greeting for multiple people in the appropriate language
                switch(lastLanguageCode) {
                    case "fr":
                        greeting = "Salutation de groupe : Bonjour à tous : " + namesBuilder.toString() + " !";
                        break;
                    case "ar":
                        greeting = "تحية جماعية: مرحباً بالجميع: " + namesBuilder.toString() + "!";
                        break;
                    case "en":
                    default:
                        greeting = "Group greeting: Hello to all: " + namesBuilder.toString() + "!";
                        break;
                }
                
                responseObserver.onNext(HelloResponse.newBuilder().setGreeting(greeting).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest> sayHellosBidirectional(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            // Map to store goodbye messages in different languages
            private final Map<String, String> goodbyeMessages = new HashMap<String, String>() {{
                put("en", "Bidirectional streaming completed. Goodbye!");
                put("fr", "Streaming bidirectionnel terminé. Au revoir!");
                put("ar", "اكتمل البث ثنائي الاتجاه. مع السلامة!");
            }};
            
            private String lastLangCode = "en"; // Track the last language code received
            
            @Override
            public void onNext(HelloRequest request) {
                logger.info("Received bidirectional request from: " + request.getFirstName());
                
                // Save the language code for the final message
                lastLangCode = request.getLanguageCode();
                if (!goodbyeMessages.containsKey(lastLangCode)) {
                    lastLangCode = "en"; // Default to English if not supported
                }
                
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
                // When client signals it's done, send final message in the appropriate language
                String goodbyeMessage = goodbyeMessages.get(lastLangCode);
                responseObserver.onNext(
                    HelloResponse.newBuilder()
                        .setGreeting(goodbyeMessage)
                        .build()
                );
                responseObserver.onCompleted();
            }
        };
    }
}