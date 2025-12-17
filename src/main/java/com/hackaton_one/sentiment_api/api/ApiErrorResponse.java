package com.hackaton_one.sentiment_api.api;

import java.time.OffsetDateTime;

/**
 * Estrutura padrão para respostas de erro da API.
 *
 * Exemplo de resposta 400 Bad Request:
 * {
 *   "timestamp": "2025-12-15T10:30:00Z",
 *   "status": 400,
 *   "error": "Bad Request",
 *   "message": "O campo 'text' é obrigatório e não pode ser vazio.",
 *   "path": "/sentiment"
 * }
 */
public class ApiErrorResponse {

    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(OffsetDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

