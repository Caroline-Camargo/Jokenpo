package com.example.jokenpo.controllers;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import com.example.jokenpo.models.JokenpoMatchModel;
import com.example.jokenpo.services.JokenpoMachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/jokenpo/match")
public class JokenpoMatchController {
    @Autowired
    JokenpoMachService jokenpoMachService;

    @PostMapping()
    public ResponseEntity<JokenpoMatchModel> createMatch(@RequestBody @Valid JokenpoMatchRecordDto matchRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jokenpoMachService.saveMatch(matchRecordDto));
    }

    @GetMapping()
    public ResponseEntity<List<JokenpoMatchModel>> getAllMatches() {
        return ResponseEntity.status(HttpStatus.OK).body(jokenpoMachService.getAllMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMatch(@PathVariable(value = "id") Long id) {
        Optional<JokenpoMatchModel> matchOptional = jokenpoMachService.getOneMatch(id);
        if (matchOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partida não encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(matchOptional.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMatch(@PathVariable(value = "id") Long id,
                                              @RequestBody @Valid JokenpoMatchRecordDto matchRecordDto) {
        Optional<JokenpoMatchModel> matchOptional = jokenpoMachService.getOneMatch(id);
        if (matchOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partida não encontrada");
        } else {
            var matchModel = matchOptional.get();
            return ResponseEntity.status(HttpStatus.CREATED).body(jokenpoMachService.updateMatch(matchModel, matchRecordDto));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMatch(@PathVariable(value = "id") Long id) {
        Optional<JokenpoMatchModel> matchOptional = jokenpoMachService.getOneMatch(id);
        if (matchOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partida não encontrada");
        } else {
            jokenpoMachService.deleteMatch(id);
            return ResponseEntity.status(HttpStatus.OK).body("Partida deletada com sucesso");
        }
    }
}
