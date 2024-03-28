package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.CategorieInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.TypeInvitation;
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


    private String generateRandomCodeInvitation() {
        // generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        // check if the iban already exists
        boolean codeMariageExists = invitationRepository.findByCodeInvitation(iban).isPresent();
        // if exists -> generate new random iban
        if (codeMariageExists) {
            generateRandomCodeInvitation();
        }
        // if not exist -> return generated iban
        return iban;
    }

    @Override
    @Transactional
    public Integer valideteInvite(Integer id) {

        InviteMariage inviters = repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("l'inviter n'est pas dans la base des donnees")
        );


        /**Generation de l'invitation par client*/

        InvitationDto invitations = InvitationDto.builder()

                .idinvite(inviters.getId())
                .codemariage(inviters.getProgramme().getCodeMariage())
                .codeInvitation(generateRandomCodeInvitation())
                .build();

        invitations.setValiditeInvitation(true);
        invitationService.save(invitations);
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
