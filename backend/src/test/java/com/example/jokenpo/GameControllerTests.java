package com.example.jokenpo;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @DirtiesContext
    @Test
    public void testPlayGame() {
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Carol", null, "papel", null, null, null);

        String[] validChoices = {"pedra", "papel", "tesoura"};
        String[] validResults = {"Carol", "Computador", "empate"};
        LocalDateTime timeNow = LocalDateTime.now();
        System.out.println("timeNow: " + timeNow);

        webTestClient
                .post()
                .uri("/jokenpo/play")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectBody()
                .jsonPath("$.player1").isEqualTo("Carol")
                .jsonPath("$.player2").isEqualTo("Computador")
                .jsonPath("$.choice1").isEqualTo("papel")
                .jsonPath("$.choice2").value(choice -> {
                    assertTrue(Arrays.asList(validChoices).contains(choice), "choice2 should be one of: pedra, papel, or tesoura");
                })
                .jsonPath("$.winner").value(result -> {
                    assertTrue(Arrays.asList(validResults).contains(result), "result should be one of: Yasmin, Computador, or Empate");
                })
                .jsonPath("$.date").value(date -> {
                    LocalDateTime matchDate = LocalDateTime.parse(date.toString());
                    System.out.println("matchDate: " + matchDate);
                    assertTrue(matchDate.isAfter(timeNow) || matchDate.isEqual(timeNow), "The match date should be either in the future or equal to the current time.");
                });
    }

    @DirtiesContext
    @Test
    public void testPlayGameWithInvalidChoice() {
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Carol", null, "invalid", null, null, null);

        webTestClient
                .post()
                .uri("/jokenpo/play")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectStatus().isNotFound();
    }

    @DirtiesContext
    @Test
    public void testPlayGameWithInvalidName() {
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto(null, null, "pedra", null, null, null);

        webTestClient
                .post()
                .uri("/jokenpo/play")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectStatus().isNotFound();
    }
}
