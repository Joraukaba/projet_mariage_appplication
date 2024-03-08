package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.DetailMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.DetailMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.DetailMariageRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.DetailMariageService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailMariageServiceImpl implements DetailMariageService {

    private final DetailMariageRepository repository;
    private final ObjectsValidator<DetailMariageDto>validator;

    @Override
    public Integer save(DetailMariageDto dto) {
        validator.validate(dto);
        DetailMariage detailMariage = DetailMariageDto.fromDto(dto);
        return repository.save(detailMariage).getId();
    }

    @Override
    public List<DetailMariageDto> findAll() {
        return repository.findAll().stream().map(
                DetailMariageDto::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public DetailMariageDto findById(Integer id) {
        return repository.findById(id).map(
                DetailMariageDto::fromEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("detail non trouve par se mariage")
        );
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
