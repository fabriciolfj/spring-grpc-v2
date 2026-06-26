package com.github.fabriciolfj.test.mapper;

import io.grpc.Status;
import org.springframework.grpc.server.advice.GrpcAdvice;
import org.springframework.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GlobalGrpcExceptionHandler {

    @GrpcExceptionHandler(IllegalArgumentException.class)
    public Status handleInvalidArgument(IllegalArgumentException e) {
        return Status.INVALID_ARGUMENT
                .withDescription(e.getMessage())
                .withCause(e);
    }

    @GrpcExceptionHandler(Exception.class)
    public Status handleGeneric(Exception e) {
        return Status.INTERNAL
                .withDescription("Erro interno inesperado")
                .withCause(e);
    }
}