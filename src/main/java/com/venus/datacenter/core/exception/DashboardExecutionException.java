package com.venus.datacenter.core.exception;

public class DashboardExecutionException extends RuntimeException {

    public DashboardExecutionException(String message) {
        super(message);
    }

    public DashboardExecutionException(Throwable cause) {
        super(cause);
    }

    public DashboardExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
