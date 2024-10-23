package com.example.jokenpo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "JOKENPO_MATCH")
public class JokenpoMatchModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String player1;
    private String player2;

    private String choice1;
    private String choice2;

    private String winner;

    private LocalDateTime date;
}
