package com.example.postsapi.common.exception;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GenericError implements Serializable {
    private String errorCode;
    private String errorMessage;

    private LocalDateTime timestamp;

    public static GenericError.GenericErrorBuilder builder() {
        return new GenericError.GenericErrorBuilder();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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

    public GenericError(String errorCode, String errorMessage, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public static class GenericErrorBuilder {
        private String errorCode;
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

        public GenericError.GenericErrorBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public GenericError.GenericErrorBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public GenericError build() {
            return new GenericError(this.errorCode, this.errorMessage, this.timestamp);
        }

        @Override
        public String toString() {
            return "GenericErrorBuilder{" +
                    "errorCode='" + errorCode + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
