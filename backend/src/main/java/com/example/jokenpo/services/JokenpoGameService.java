package com.example.jokenpo.services;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JokenpoGameService {

    @Autowired
    private JokenpoMachService jokenpoMachService;

    public JokenpoMatchRecordDto playGame(String namePlayer1, String player1option) {
        String namePlayer2 = "Computador";
        String player2option = computerPlay();
        player1option = convertOption(player1option);
        String result = checkVitory(namePlayer1, player1option, namePlayer2, player2option);
        LocalDateTime matchDate = LocalDateTime.now();

        JokenpoMatchRecordDto matchRecordDto = new JokenpoMatchRecordDto(namePlayer1, namePlayer2, player1option, player2option, result, matchDate);
        jokenpoMachService.saveMatch(matchRecordDto);

        return matchRecordDto;
    }
    public String computerPlay() {
        String[] options = {"pedra", "papel", "tesoura"};
        int randomIndex = (int) (Math.random() * options.length);
        return options[randomIndex];
    }

    public String convertOption(String option) {
        if (option.equals("pedra") || option.equals("papel") || option.equals("tesoura")) {
            return option;
        }
        if (option.equals("rock")) {
            return "pedra";
        }
        if (option.equals("paper")) {
            return "papel";
        }
        if (option.equals("scissors")) {
            return "tesoura";
        }
        return null;
    }

    public String checkVitory(String namePlayer1, String player1option, String namePlayer2, String player2option) {
        if (player1option.equals(player2option)) {
            return "empate";
        }
        if (player1option.equals("pedra") && player2option.equals("tesoura")) {
            return namePlayer1;
        }
        if (player1option.equals("tesoura") && player2option.equals("papel")) {
            return namePlayer1;
        }
        if (player1option.equals("papel") && player2option.equals("pedra")) {
            return namePlayer1;
        }
        return namePlayer2;
    }
}
