package com.hackaton_one.sentiment_api.api;

import com.hackaton_one.sentiment_api.service.SentimentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller principal da API de análise de sentimento.
 *
 * Endpoint: POST /sentiment
 *
 * Exemplo de requisição:
 * POST /sentiment
 * Content-Type: application/json
 *
 * {
 *   "text": "Este produto é muito bom!"
 * }
 *
 * Exemplo de resposta 200 OK:
 * {
 *   "sentiment": "POSITIVE",
 *   "score": 0.9,
 *   "text": "Este produto é muito bom!"
 * }
 *
 * Exemplo de resposta 400 Bad Request (corpo inválido/faltando campo obrigatório):
 * {
 *   "status": 400,
 *   "error": "Bad Request",
 *   "message": "O campo 'text' é obrigatório e não pode ser vazio.",
 *   "path": "/sentiment"
 * }
 */
@RestController
@RequestMapping(path = "/sentiment", produces = MediaType.APPLICATION_JSON_VALUE)
public class SentimentController {

    private final SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SentimentResponse> analyzeSentiment(
            @Valid @RequestBody SentimentRequest request) {

        SentimentResponse response = sentimentService.analyze(request.getText());
        return ResponseEntity.ok(response);
    }
}

