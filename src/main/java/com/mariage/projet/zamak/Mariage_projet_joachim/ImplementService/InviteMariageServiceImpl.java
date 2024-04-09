package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.*;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.*;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InvitationService;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InviteMariageService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InviteMariageServiceImpl  implements InviteMariageService{

    private final InviteRepositoris repository;
    private final InvitationRepository invitationRepository;

    private final ProgrammeRepository programmeRepository;

    private final TypeInvitationRepository typeInvitationRepository;

    private final CategorieRepository categorieRepository;

    private  final InvitationService invitationService;
    private final ObjectsValidator<InviteMariageDto>validator;

    @Override
    public Integer save(InviteMariageDto dto) {
        validator.validate(dto);
        InviteMariage inviters = InviteMariageDto.formDto(dto);

        ProgrammaeMariage programmaeMariage = programmeRepository.findById(dto.getId_programme())
                .orElseThrow(
                        ()-> new EntityNotFoundException("le programme de reference ne pas dans la base des donnees")
                );
        CategorieInvite categorieInvite = categorieRepository.findById(dto.getId_categorieInvite())
                .orElseThrow(
                        ()-> new EntityNotFoundException("categorie referencer ne pas dans la base des donnees")
                );
        TypeInvitation typeInvitation = typeInvitationRepository.findById(dto.getId_typeInvitation())
                .orElseThrow(
                        ()-> new EntityNotFoundException("type de l'invitation non trouve dans la base")
                );
        inviters.setProgramme(programmaeMariage);
        inviters.setCategorieInvite(categorieInvite);
        inviters.setTypeInvitation(typeInvitation);
        return repository.save(inviters).getId();
    }

    @Override
    public List<InviteMariageDto> findAll() {
        return repository.findAll().stream().map(
                InviteMariageDto::formEntity
        ).collect(Collectors.toList());
    }

    @Override
    public InviteMariageDto findById(Integer id) {
        return repository.findById(id).map(
                InviteMariageDto::formEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("Invite non trouve dans la base des donnees")
        );
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            throw new EntityNotFoundException("Invite non trouve dans la base des donnees");
        }
    }


    @Override
    @Transactional
    public Integer valideteInvite(Integer id) {

        InviteMariage inviters = repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("l'inviter n'est pas dans la base des donnees")
        );
//        boolean AlreadyInvitationValide = invitationRepository.findByInviteMariageIdvalide(inviters.getId()).isPresent();
//
//        if (AlreadyInvitationValide && inviters.getInvitations().isValiditeInvitation()){
//            throw  new EntityNotFoundException("Cette invite a deja une invitation valide");
//        }

        /**Generation de l'invitation par client*/
        InvitationDto invitations = InvitationDto.builder()

                .idinvite(inviters.getId())
                .codemariage(inviters.getProgramme().getCodeMariage())
                .typeinvite(inviters.getTypeInvitation().getId())
                .typeinv(inviters.getTypeInvitation().getDescription())
                .categorieinvite(inviters.getCategorieInvite().getId())
                .categorieinv(inviters.getCategorieInvite().getLibelle())
                .nomCompletinv(inviters.getNomComplete())
                .programmaeMariage(inviters.getProgramme().getId())
                .build();

        invitations.setValiditeInvitation(true);
        invitationService.save(invitations);
        repository.save(inviters);

        return inviters.getId();

    }

    @Override
    public List<InviteMariageDto> searchInviteMariage(String keyword) {
        return repository.searchByNomCompleteIgnoreCase(keyword).stream().map(
                InviteMariageDto::formEntity
        ).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<InviteMariageDto> findAllById(Integer id) {
        if(id==null){
            throw new EntityNotFoundException("pas des invites par rapport a se programme");
        }
        return repository.findAllByProgrammeId(id).stream().map(
                InviteMariageDto::formEntity
        ).collect(Collectors.toList());
    }




}
