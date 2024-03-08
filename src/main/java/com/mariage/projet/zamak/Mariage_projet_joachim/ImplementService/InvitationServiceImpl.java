package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.InvitationRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InvitationService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {
    private final InvitationRepository repository;

    private final ObjectsValidator<InvitationDto>validator;

    @Override
    public Integer save(InvitationDto dto) {
        validator.validate(dto);
        Invitations invitations = InvitationDto.fromDto(dto);
        return repository.save(invitations).getId();
    }

    @Override
    public List<InvitationDto> findAll() {
        return repository.findAll().stream().map(
                InvitationDto::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public InvitationDto findById(Integer id) {
        return repository.findById(id).map(
                InvitationDto::fromEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("Invitation introuvable")
        );
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            throw new EntityNotFoundException("Invitation introuvable");
        }

        repository.deleteById(id);
    }
};
