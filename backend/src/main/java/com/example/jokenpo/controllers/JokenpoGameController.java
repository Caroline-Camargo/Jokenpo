package com.example.jokenpo.controllers;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import com.example.jokenpo.services.JokenpoGameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JokenpoGameController {
    @Autowired
    private JokenpoGameService jokenpoGameService;

    @GetMapping("/jokenpo/play/{namePlayer1}/{player1option}")
    public ResponseEntity<String> playGame(@PathVariable String namePlayer1, @PathVariable String player1option) {
        String result = jokenpoGameService.playGame(namePlayer1, player1option);
        if (result.equals("Invalid option!")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid option! Please choose rock, paper, or scissors.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/jokenpo/play")
    public ResponseEntity<String> playGamePost(@RequestBody JokenpoMatchRecordDto matchRecordDto) {
        if (matchRecordDto.player1() == null || matchRecordDto.player1().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player name field is required.");
        } else if (matchRecordDto.choice1() == null || matchRecordDto.choice1().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player choice field is required.");
        }
        String result = jokenpoGameService.playGame(matchRecordDto.player1(), matchRecordDto.choice1());
        if (result.equals("Invalid option!")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid option! Please choose rock, paper, or scissors.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
