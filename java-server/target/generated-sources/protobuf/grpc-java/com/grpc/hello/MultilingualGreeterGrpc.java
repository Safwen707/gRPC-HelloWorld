package com.grpc.hello;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.1)",
    comments = "Source: hello_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MultilingualGreeterGrpc {

  private MultilingualGreeterGrpc() {}

  public static final String SERVICE_NAME = "hello.MultilingualGreeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.grpc.hello.HelloRequest.class,
      responseType = com.grpc.hello.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse> getSayHelloMethod;
    if ((getSayHelloMethod = MultilingualGreeterGrpc.getSayHelloMethod) == null) {
      synchronized (MultilingualGreeterGrpc.class) {
        if ((getSayHelloMethod = MultilingualGreeterGrpc.getSayHelloMethod) == null) {
          MultilingualGreeterGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MultilingualGreeterMethodDescriptorSupplier("SayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHellosServerStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHellosServerStreaming",
      requestType = com.grpc.hello.HelloRequest.class,
      responseType = com.grpc.hello.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHellosServerStreamingMethod() {
    io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse> getSayHellosServerStreamingMethod;
    if ((getSayHellosServerStreamingMethod = MultilingualGreeterGrpc.getSayHellosServerStreamingMethod) == null) {
      synchronized (MultilingualGreeterGrpc.class) {
        if ((getSayHellosServerStreamingMethod = MultilingualGreeterGrpc.getSayHellosServerStreamingMethod) == null) {
          MultilingualGreeterGrpc.getSayHellosServerStreamingMethod = getSayHellosServerStreamingMethod =
              io.grpc.MethodDescriptor.<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHellosServerStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MultilingualGreeterMethodDescriptorSupplier("SayHellosServerStreaming"))
              .build();
        }
      }
    }
    return getSayHellosServerStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHellosClientStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHellosClientStreaming",
      requestType = com.grpc.hello.HelloRequest.class,
      responseType = com.grpc.hello.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHellosClientStreamingMethod() {
    io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse> getSayHellosClientStreamingMethod;
    if ((getSayHellosClientStreamingMethod = MultilingualGreeterGrpc.getSayHellosClientStreamingMethod) == null) {
      synchronized (MultilingualGreeterGrpc.class) {
        if ((getSayHellosClientStreamingMethod = MultilingualGreeterGrpc.getSayHellosClientStreamingMethod) == null) {
          MultilingualGreeterGrpc.getSayHellosClientStreamingMethod = getSayHellosClientStreamingMethod =
              io.grpc.MethodDescriptor.<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHellosClientStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MultilingualGreeterMethodDescriptorSupplier("SayHellosClientStreaming"))
              .build();
        }
      }
    }
    return getSayHellosClientStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHellosBidirectionalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHellosBidirectional",
      requestType = com.grpc.hello.HelloRequest.class,
      responseType = com.grpc.hello.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest,
      com.grpc.hello.HelloResponse> getSayHellosBidirectionalMethod() {
    io.grpc.MethodDescriptor<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse> getSayHellosBidirectionalMethod;
    if ((getSayHellosBidirectionalMethod = MultilingualGreeterGrpc.getSayHellosBidirectionalMethod) == null) {
      synchronized (MultilingualGreeterGrpc.class) {
        if ((getSayHellosBidirectionalMethod = MultilingualGreeterGrpc.getSayHellosBidirectionalMethod) == null) {
          MultilingualGreeterGrpc.getSayHellosBidirectionalMethod = getSayHellosBidirectionalMethod =
              io.grpc.MethodDescriptor.<com.grpc.hello.HelloRequest, com.grpc.hello.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHellosBidirectional"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.hello.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MultilingualGreeterMethodDescriptorSupplier("SayHellosBidirectional"))
              .build();
        }
      }
    }
    return getSayHellosBidirectionalMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MultilingualGreeterStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MultilingualGreeterStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MultilingualGreeterStub>() {
        @java.lang.Override
        public MultilingualGreeterStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MultilingualGreeterStub(channel, callOptions);
        }
      };
    return MultilingualGreeterStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MultilingualGreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MultilingualGreeterBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MultilingualGreeterBlockingStub>() {
        @java.lang.Override
        public MultilingualGreeterBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MultilingualGreeterBlockingStub(channel, callOptions);
        }
      };
    return MultilingualGreeterBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MultilingualGreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MultilingualGreeterFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MultilingualGreeterFutureStub>() {
        @java.lang.Override
        public MultilingualGreeterFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MultilingualGreeterFutureStub(channel, callOptions);
        }
      };
    return MultilingualGreeterFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class MultilingualGreeterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Unary RPC - Simple request/response
     * </pre>
     */
    public void sayHello(com.grpc.hello.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server streaming RPC - Server sends multiple responses
     * </pre>
     */
    public void sayHellosServerStreaming(com.grpc.hello.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHellosServerStreamingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Client streaming RPC - Client sends multiple requests
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.hello.HelloRequest> sayHellosClientStreaming(
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSayHellosClientStreamingMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC - Both sides send a sequence of messages
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.hello.HelloRequest> sayHellosBidirectional(
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSayHellosBidirectionalMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.grpc.hello.HelloRequest,
                com.grpc.hello.HelloResponse>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSayHellosServerStreamingMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.hello.HelloRequest,
                com.grpc.hello.HelloResponse>(
                  this, METHODID_SAY_HELLOS_SERVER_STREAMING)))
          .addMethod(
            getSayHellosClientStreamingMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.grpc.hello.HelloRequest,
                com.grpc.hello.HelloResponse>(
                  this, METHODID_SAY_HELLOS_CLIENT_STREAMING)))
          .addMethod(
            getSayHellosBidirectionalMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.grpc.hello.HelloRequest,
                com.grpc.hello.HelloResponse>(
                  this, METHODID_SAY_HELLOS_BIDIRECTIONAL)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MultilingualGreeterStub extends io.grpc.stub.AbstractAsyncStub<MultilingualGreeterStub> {
    private MultilingualGreeterStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MultilingualGreeterStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MultilingualGreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC - Simple request/response
     * </pre>
     */
    public void sayHello(com.grpc.hello.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server streaming RPC - Server sends multiple responses
     * </pre>
     */
    public void sayHellosServerStreaming(com.grpc.hello.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSayHellosServerStreamingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Client streaming RPC - Client sends multiple requests
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.hello.HelloRequest> sayHellosClientStreaming(
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getSayHellosClientStreamingMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC - Both sides send a sequence of messages
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.hello.HelloRequest> sayHellosBidirectional(
        io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSayHellosBidirectionalMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MultilingualGreeterBlockingStub extends io.grpc.stub.AbstractBlockingStub<MultilingualGreeterBlockingStub> {
    private MultilingualGreeterBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MultilingualGreeterBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MultilingualGreeterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC - Simple request/response
     * </pre>
     */
    public com.grpc.hello.HelloResponse sayHello(com.grpc.hello.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server streaming RPC - Server sends multiple responses
     * </pre>
     */
    public java.util.Iterator<com.grpc.hello.HelloResponse> sayHellosServerStreaming(
        com.grpc.hello.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSayHellosServerStreamingMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class MultilingualGreeterFutureStub extends io.grpc.stub.AbstractFutureStub<MultilingualGreeterFutureStub> {
    private MultilingualGreeterFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MultilingualGreeterFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MultilingualGreeterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC - Simple request/response
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.hello.HelloResponse> sayHello(
        com.grpc.hello.HelloRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_HELLOS_SERVER_STREAMING = 1;
  private static final int METHODID_SAY_HELLOS_CLIENT_STREAMING = 2;
  private static final int METHODID_SAY_HELLOS_BIDIRECTIONAL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MultilingualGreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MultilingualGreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.grpc.hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse>) responseObserver);
          break;
        case METHODID_SAY_HELLOS_SERVER_STREAMING:
          serviceImpl.sayHellosServerStreaming((com.grpc.hello.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLOS_CLIENT_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHellosClientStreaming(
              (io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse>) responseObserver);
        case METHODID_SAY_HELLOS_BIDIRECTIONAL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHellosBidirectional(
              (io.grpc.stub.StreamObserver<com.grpc.hello.HelloResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MultilingualGreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MultilingualGreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.hello.HelloWorldProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MultilingualGreeter");
    }
  }

  private static final class MultilingualGreeterFileDescriptorSupplier
      extends MultilingualGreeterBaseDescriptorSupplier {
    MultilingualGreeterFileDescriptorSupplier() {}
  }

  private static final class MultilingualGreeterMethodDescriptorSupplier
      extends MultilingualGreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MultilingualGreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MultilingualGreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MultilingualGreeterFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSayHellosServerStreamingMethod())
              .addMethod(getSayHellosClientStreamingMethod())
              .addMethod(getSayHellosBidirectionalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
