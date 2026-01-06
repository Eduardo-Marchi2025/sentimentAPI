package com.hackaton_one.sentiment_api.integration;

import com.hackaton_one.sentiment_api.api.controller.SentimentController;
import com.hackaton_one.sentiment_api.api.dto.SentimentResultDTO;
import com.hackaton_one.sentiment_api.repository.SentimentRepository;
import com.hackaton_one.sentiment_api.service.BatchService;
import com.hackaton_one.sentiment_api.service.SentimentPersistenceService;
import com.hackaton_one.sentiment_api.service.SentimentService;
import com.hackaton_one.sentiment_api.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SentimentController.class)
public class SentimentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SentimentPersistenceService sentimentPersistenceService;

    @MockitoBean
    private SentimentService sentimentService;

    @MockitoBean
    private BatchService batchService;

    @MockitoBean
    private StatisticsService statisticsService;

    @MockitoBean
    private SentimentRepository sentimentRepository;

    /* Test analyze sentiment single text endpoint */
    @Test
    void shouldReturn200WhenSendingPostToAnalyzeWithPositiveSentiment() throws Exception {
        when(sentimentService.analyze(anyString())).thenReturn(new SentimentResultDTO("POSITIVO", 0.95));

        mockMvc.perform(post("/sentiment").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                            "text": "muito bom"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("POSITIVO"))
                .andExpect(jsonPath("$.score").isNumber())
                .andExpect(jsonPath("$.text").value("muito bom"));

    }

    @Test
    void shouldReturn200WhenSendingPostToAnalyzeWithNegativeSentiment() throws Exception {
        when(sentimentService.analyze(anyString())).thenReturn(new SentimentResultDTO("NEGATIVO", 0.95));

        mockMvc.perform(post("/sentiment").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                            "text": "muito ruim"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentiment").value("NEGATIVO"))
                .andExpect(jsonPath("$.score").isNumber())
                .andExpect(jsonPath("$.text").value("muito ruim"));

    }

    @Test
    void shouldReturn400WhenSendingPostToAnalyzeWithEmptyText() throws Exception {
        mockMvc.perform(post("/sentiment").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                            "text": ""
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenSendingPostToAnalyzeWithMissingText() throws Exception {
        mockMvc.perform(post("/sentiment").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn200EvenWhenPersistenceServiceThrowsException() throws Exception {
        when(sentimentService.analyze(anyString())).thenReturn(new SentimentResultDTO("POSITIVO", 0.95));
        when(sentimentPersistenceService.saveSentiment(anyString(), anyString(), anyDouble()))
                .thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(post("/sentiment").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                            "text": "teste de erro"
                        }
                        """))
                .andExpect(status().isOk()) // Espera 200 OK pois o erro é tratado (logado e ignorado)
                .andExpect(jsonPath("$.sentiment").value("POSITIVO"))
                .andExpect(jsonPath("$.score").value(0.95));
    }

    /* Test analyze sentiment for CSV batch */
    // TODO: Implement batch tests

    /* Test statistics endpoint */
    // TODO: Implement statistics tests

    /* Test history endpoint */
    // TODO: Implement history tests
}


