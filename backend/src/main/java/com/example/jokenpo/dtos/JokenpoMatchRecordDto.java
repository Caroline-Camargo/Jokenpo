package com.example.jokenpo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record JokenpoMatchRecordDto(
        @NotBlank String player1,
        @NotBlank String player2,
        String player1Choice,
        String player2Choice,
        String winner,
        @NotNull LocalDateTime matchDate
) {
}
