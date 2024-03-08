package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.TypeInvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.TypeInvitation;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.TypeInvitationRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.TypeInvitationService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeinvitationServiceImpl  implements TypeInvitationService {
    private final TypeInvitationRepository repository;

    private final ObjectsValidator<TypeInvitationDto>validator;


    @Override
    public Integer save(TypeInvitationDto dto) {
        validator.validate(dto);
        TypeInvitation typeInvitation = TypeInvitationDto.fromDto(dto);
        return repository.save(typeInvitation).getId();
    }

    @Override
    public List<TypeInvitationDto> findAll() {
        return repository.findAll().stream().map(
                TypeInvitationDto::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public TypeInvitationDto findById(Integer id) {
        return repository.findById(id).map(
                TypeInvitationDto::fromEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("type de l'invitation non trouve")
        );
    }

    @Override
    public void delete(Integer id) {
        if(id== null){
            throw new EntityNotFoundException("type de l'invitation non trouve");
        }

        repository.deleteById(id);
    }
}
