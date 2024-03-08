package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.InvitationRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.InviteRepositoris;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InvitationService;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InviteMariageService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InviteMariageServiceImpl  implements InviteMariageService{

    private final InviteRepositoris repository;
    private final InvitationRepository invitationRepository;

    private  final InvitationService invitationService;
    private final ObjectsValidator<InviteMariageDto>validator;

    @Override
    public Integer save(InviteMariageDto dto) {
        validator.validate(dto);
        InviteMariage inviters = InviteMariageDto.formDto(dto);
        valideteInvite(inviters.getId());
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
    public Integer valideteInvite(Integer id) {

        InviteMariage inviters = repository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("l'invite possede deja l'invitation")
        );
        /**Generation de l'invitation par client*/

        InvitationDto invitations = InvitationDto.builder()
                .idcategorie(inviters.getCategorieInvite().getId())
                .idinvite(inviters.getId())
                .codemariage(inviters.getProgramme().getCodeMariage())
                .codeInvitation(generateRandomCodeInvitation())
                .typeinvitation(inviters.getTypeInvitation().getId())
                .build();

        invitations.setValiditeInvitation(true);
        invitationService.save(invitations);
        return inviters.getId();
    }
}
