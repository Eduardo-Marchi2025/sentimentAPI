package com.hackaton_one.sentiment_api.api;

import jakarta.validation.constraints.NotBlank;

/**
 * Payload de entrada para análise de sentimento.
 */
public class SentimentRequest {

    @NotBlank(message = "O campo 'text' é obrigatório e não pode ser vazio.")
    private String text;

    public SentimentRequest() {
    }

    public SentimentRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

