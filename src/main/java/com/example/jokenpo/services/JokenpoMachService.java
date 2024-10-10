package com.example.jokenpo.services;

import com.example.jokenpo.dtos.JokenpoMatchRecordDto;
import com.example.jokenpo.models.JokenpoMatchModel;
import com.example.jokenpo.repositories.JokenpoMatchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JokenpoMachService {
    @Autowired
    JokenpoMatchRepository jokenpoMatchRepository;

    public JokenpoMatchModel saveMatch(JokenpoMatchRecordDto matchRecordDto) {
        var matchModel = new JokenpoMatchModel();
        BeanUtils.copyProperties(matchRecordDto, matchModel);
        return jokenpoMatchRepository.save(matchModel);
    }

    public List<JokenpoMatchModel> getAllMatches() {
        return jokenpoMatchRepository.findAll();
    }

    public Optional<JokenpoMatchModel> getOneMatch(Long id) {
        return jokenpoMatchRepository.findById(id);
    }

    public JokenpoMatchModel updateMatch(JokenpoMatchModel matchModel, JokenpoMatchRecordDto matchRecordDto) {
        BeanUtils.copyProperties(matchRecordDto, matchModel);
        return jokenpoMatchRepository.save(matchModel);
    }

    public void deleteMatch(Long id) {
        jokenpoMatchRepository.deleteById(id);
    }

}
