package com.hackaton_one.sentiment_api.service;

import com.hackaton_one.sentiment_api.api.SentimentResponse;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por executar (ou delegar) a lógica de análise de sentimento.
 * <p>
 * Atualmente implementa uma lógica mock/stub apenas para fins de exemplo.
 */
@Service
public class SentimentService {

    public SentimentResponse analyze(String text) {
        if (text == null || text.isBlank()) {
            // Esta validação normalmente é feita na camada de controller via Bean Validation,
            // mas aqui garantimos um fallback defensivo.
            throw new IllegalArgumentException("Texto para análise não pode ser nulo ou vazio.");
        }

        // Lógica mock de sentimento: apenas como exemplo simples.
        String lower = text.toLowerCase();
        String sentiment;
        double score;

        if (lower.contains("bom") || lower.contains("good") || lower.contains("ótimo") || lower.contains("excelente")) {
            sentiment = "POSITIVE";
            score = 0.9;
        } else if (lower.contains("ruim") || lower.contains("bad") || lower.contains("péssimo") || lower.contains("horrível")) {
            sentiment = "NEGATIVE";
            score = 0.1;
        } else {
            sentiment = "NEUTRAL";
            score = 0.5;
        }

        return new SentimentResponse(sentiment, score, text);
    }
}

