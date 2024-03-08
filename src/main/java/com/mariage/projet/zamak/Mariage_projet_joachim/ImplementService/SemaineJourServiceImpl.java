package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.SemaineJourDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.SemaineJour;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.SemaineJourRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.SemaineJourService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemaineJourServiceImpl implements SemaineJourService {

    private final SemaineJourRepository repository;

    private final ObjectsValidator<SemaineJourDto>validator;

    @Override
    public Integer save(SemaineJourDto dto) {
        validator.validate(dto);
        SemaineJour semaineJour = SemaineJourDto.fromDto(dto);
        return repository.save(semaineJour).getId();
    }

    @Override
    public List<SemaineJourDto> findAll() {
        return repository.findAll().stream().map(
                SemaineJourDto::formEntity
        ).collect(Collectors.toList());
    }

    @Override
    public SemaineJourDto findById(Integer id) {
        return repository.findById(id).map(
                SemaineJourDto::formEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("jour de la semaine non trouver")
        );
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
