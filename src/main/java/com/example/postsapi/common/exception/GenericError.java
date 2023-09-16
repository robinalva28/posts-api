package com.example.postsapi.common.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GenericError implements Serializable {
    private String errorMessage;

    private LocalDateTime timestamp;

    public static GenericError.GenericErrorBuilder builder() {
        return new GenericError.GenericErrorBuilder();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public GenericError(String errorMessage, LocalDateTime timestamp) {
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public static class GenericErrorBuilder {
        private String errorMessage;

        private LocalDateTime timestamp;

        GenericErrorBuilder() {
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }


        public GenericError.GenericErrorBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public GenericError build() {
            return new GenericError(this.errorMessage, this.timestamp);
        }

        @Override
        public String toString() {
            return "GenericErrorBuilder{" +
                    ", errorMessage='" + errorMessage + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
