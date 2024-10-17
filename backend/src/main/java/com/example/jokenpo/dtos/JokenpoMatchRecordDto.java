package com.example.jokenpo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record JokenpoMatchRecordDto(
        @NotNull @NotBlank String player1,
        @NotNull @NotBlank String player2,
        @NotNull String choice1,
        @NotNull String choice2,
        @NotNull String winner,
        @NotNull LocalDateTime date
) {
}
