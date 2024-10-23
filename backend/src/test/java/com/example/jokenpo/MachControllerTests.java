package com.example.jokenpo;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MachControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Caroline", "Computador", "pedra", "tesoura", "Carol", dateTime);
        webTestClient
                .post()
                .uri("/jokenpo/match")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DirtiesContext
    public void testCreateMatch() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Caroline", "Computador", "tesoura", "tesoura", "empate", dateTime);
        webTestClient
                .post()
                .uri("/jokenpo/match")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DirtiesContext
    public void testCreateMatchWinnerPlayer1() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Caroline", "Computador", "pedra", "tesoura", "Carol", dateTime);
        webTestClient
                .post()
                .uri("/jokenpo/match")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectBody()
                .jsonPath("$.winner").isEqualTo("Carol");
    }

    @Test
    @DirtiesContext
    public void testCreateMatchWinnerPlayer2() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Caroline", "Computador", "tesoura", "pedra", "Computador", dateTime);
        webTestClient
                .post()
                .uri("/jokenpo/match")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectBody()
                .jsonPath("$.winner").isEqualTo("Computador");
    }

    @Test
    @DirtiesContext
    public void testGetMatch() {
        webTestClient
                .get()
                .uri("/jokenpo/match/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.player1").isEqualTo("Caroline")
                .jsonPath("$.player2").isEqualTo("Computador")
                .jsonPath("$.choice1").isEqualTo("pedra")
                .jsonPath("$.choice2").isEqualTo("tesoura")
                .jsonPath("$.winner").isEqualTo("Carol")
                .jsonPath("$.date").isEqualTo("2024-10-21T10:02:56.743434");
    }

    @Test
    @DirtiesContext
    public void testGetMatchNotFound() {
        webTestClient
                .get()
                .uri("/jokenpo/match/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DirtiesContext
    public void testDeleteMatch() {
        // Verifique se a partida com ID 1 existe
        webTestClient
                .get()
                .uri("/jokenpo/match/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.player1").isEqualTo("Caroline")
                .jsonPath("$.player2").isEqualTo("Computador")
                .jsonPath("$.choice1").isEqualTo("pedra")
                .jsonPath("$.choice2").isEqualTo("tesoura")
                .jsonPath("$.winner").isEqualTo("Carol");

        // Agora, chame o método de delete
        webTestClient
                .delete()
                .uri("/jokenpo/match/1")
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    @DirtiesContext
    public void testDeleteMatchNotFound() {
        webTestClient
                .delete()
                .uri("/jokenpo/match/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DirtiesContext
    public void testeUpdateMatch() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Caroline", "Computador", "pedra", "tesoura", "Carol", dateTime);
        webTestClient
                .put()
                .uri("/jokenpo/match/1")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DirtiesContext
    public void testeUpdateMatchNotFound() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Fulano", "Computador", "pedra", "tesoura", "Carol", dateTime);
        webTestClient
                .put()
                .uri("/jokenpo/match/999")
                .bodyValue(matchRecordDto)
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    @DirtiesContext
    public void testGetAllMatches() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 21, 10, 2, 56, 743434000); // ano, mês, dia, hora, minuto, segundo, nanossegundos
        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto("Caroline", "Computador", "pedra", "tesoura", "Caroline", dateTime);
        testCreateMatchWinnerPlayer2();

        webTestClient
                .get()
                .uri("/jokenpo/match")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].player1").isEqualTo("Caroline")
                .jsonPath("$[0].player2").isEqualTo("Computador")
                .jsonPath("$[0].choice1").isEqualTo("pedra")
                .jsonPath("$[0].choice2").isEqualTo("tesoura")
                .jsonPath("$[0].winner").isEqualTo("Carol")
                .jsonPath("$[0].date").isEqualTo("2024-10-21T10:02:56.743434")
                .jsonPath("$[1].player1").isEqualTo("Caroline")
                .jsonPath("$[1].player2").isEqualTo("Computador")
                .jsonPath("$[1].choice1").isEqualTo("tesoura")
                .jsonPath("$[1].choice2").isEqualTo("pedra")
                .jsonPath("$[1].winner").isEqualTo("Computador")
                .jsonPath("$[1].date").isEqualTo("2024-10-21T10:02:56.743434");
    }
}
