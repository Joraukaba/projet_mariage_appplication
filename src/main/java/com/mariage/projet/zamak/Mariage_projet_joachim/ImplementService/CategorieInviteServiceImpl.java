package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.CategorieInviteDTO;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.DetailMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.CategorieInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.CategorieRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.CategorieInviteService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieInviteServiceImpl implements CategorieInviteService {

    private final CategorieRepository repository;
    private final ObjectsValidator<CategorieInviteDTO> validator;

    @Override
    public Integer save(CategorieInviteDTO dto) {
        validator.validate(dto);
        CategorieInvite categorieInvite = CategorieInviteDTO.formDto(dto);
        return repository.save(categorieInvite).getId();
    }

    @Override
    public List<CategorieInviteDTO> findAll() {
        return repository.findAll().stream().map(
                CategorieInviteDTO::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public CategorieInviteDTO findById(Integer id) {
        return repository.findById(id).map(
                CategorieInviteDTO::fromEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("Categorie non trouve")
        );
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            throw new EntityNotFoundException("Categorie non trouver");
        }

        repository.deleteById(id);
    }
}
