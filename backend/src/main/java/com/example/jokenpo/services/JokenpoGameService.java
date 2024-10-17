package com.example.jokenpo.services;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JokenpoGameService {

    @Autowired
    private JokenpoMachService jokenpoMachService;

    public String playGame(String namePlayer1, String player1option) {
        String namePlayer2 = "Computer";
        String player2option = computerPlay();
        player1option = convertOption(player1option);
        String result = checkVitory(namePlayer1, player1option, namePlayer2, player2option);
        LocalDateTime matchDate = LocalDateTime.now();

        System.out.println("Player 1: " + namePlayer1 + " - " + player1option);
        System.out.println("Player 2: " + namePlayer2 + " - " + player2option);
        System.out.println("Winner: " + result);
        System.out.println("Match Date: " + matchDate);


        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto(namePlayer1, namePlayer2, player1option, player2option, result, matchDate);
        jokenpoMachService.saveMatch(matchRecordDto);

        return result;
    }
    public String computerPlay() {
        String[] options = {"rock", "paper", "scissors"};
        int randomIndex = (int) (Math.random() * options.length);
        return options[randomIndex];
    }

    public String convertOption(String option) {
        if (option.equals("rock") || option.equals("paper") || option.equals("scissors")) {
            return option;
        }
        if (option.equals("pedra")) {
            return "rock";
        }
        if (option.equals("papel")) {
            return "paper";
        }
        if (option.equals("tesoura")) {
            return "scissors";
        }
        return "Invalid option!";
    }

    public String checkVitory(String namePlayer1, String player1option, String namePlayer2, String player2option) {
        if (player1option.equals(player2option)) {
            return "draw";
        }
        if (player1option.equals("rock") && player2option.equals("scissors")) {
            return namePlayer1;
        }
        if (player1option.equals("scissors") && player2option.equals("paper")) {
            return namePlayer1;
        }
        if (player1option.equals("paper") && player2option.equals("rock")) {
            return namePlayer1;
        }
        return namePlayer2;
    }
}
