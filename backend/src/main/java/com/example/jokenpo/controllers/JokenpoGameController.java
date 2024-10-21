package com.example.jokenpo.controllers;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import com.example.jokenpo.services.JokenpoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/jokenpo/play")
public class JokenpoGameController {
    @Autowired
    private JokenpoGameService jokenpoGameService;

    @GetMapping("/{namePlayer1}/{player1option}")
    public ResponseEntity<Object> playGame(@PathVariable String namePlayer1, @PathVariable String player1option) {
        JokenpoMatchRecordDto result = jokenpoGameService.playGame(namePlayer1, player1option);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping()
    public ResponseEntity<Object> playGamePost(@RequestBody JokenpoMatchRecordDto matchRecordDto) {
        if (matchRecordDto.player1() == null || matchRecordDto.player1().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O campo nome do jogador é obrigatório.");
        } else if (matchRecordDto.choice1() == null || matchRecordDto.choice1().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O jogador precisa escolher uma opção (pedra, papel ou tesoura).");
        }
        JokenpoMatchRecordDto result = jokenpoGameService.playGame(matchRecordDto.player1(), matchRecordDto.choice1());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
