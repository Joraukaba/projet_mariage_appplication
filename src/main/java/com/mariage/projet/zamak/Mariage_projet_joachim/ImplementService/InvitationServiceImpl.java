package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Exceptions.OperationNonPermittedException;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.InvitationRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.InviteRepositoris;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.PresenceInviteRepositiry;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.ProgrammeRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InvitationService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {
    private final InvitationRepository repository;
    private final InvitationRepository invitationRepository;

    private final ProgrammeRepository programmeRepository;
    private final PresenceInviteRepositiry presenceInviteRepositiry;

    private final InviteRepositoris inviteRepositorisl;
    private final ObjectsValidator<InvitationDto>validator;

    @Override
    public Integer save(InvitationDto dto) {
        validator.validate(dto);
        Invitations invitations = InvitationDto.fromDto(dto);
        boolean AlreadyInviteExist = repository.findByInviteMariageIdvalide(invitations.getInviteMariage().getId()).isPresent();

        if (AlreadyInviteExist){
            throw new EntityNotFoundException("Patron est la deja");
        }

        if (dto.getId() == null){
            invitations.setCodeInvitation(generateRandomCodeInvitation());
            invitations.setCodesecret(generateRandomNumber());
        }

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

    @Override
    public InvitationDto findByInviteId(Integer id) {
        return repository.findByInviteMariageId(id).map(
                InvitationDto::fromEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("l'invite possede deja une invitation")
        );
    }

    @Override
    public List<InvitationDto> finAllByInvite(Integer id) {
        return repository.findAllByInviteMariageId(id).stream().map(
                InvitationDto::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public String invalidateInvitation(String code) {
        Invitations invitations = repository.findByCodeInvitation(code).orElseThrow(
                ()-> new EntityNotFoundException("l'invitation n'est pas trouver")
        );

        invitations.setValiditeInvitation(false);
        return repository.save(invitations).getCodeInvitation();
    }

    @Override
    public InvitationDto findByCodeInvitation(String code) {

        InvitationDto invitation_deja_utiliser = repository.findByCodeInvitationVerifier(code).map(
                InvitationDto::fromEntity

        ).orElseThrow(
                () -> new EntityNotFoundException("Invitation deja utiliser")
        );
        invitation_deja_utiliser.setValiditeInvitation(false);

        //la presence des invites
        PresentInviteDto presentInviteDto = PresentInviteDto.builder()
                .statut(true)
                .date(LocalDateTime.now())
                .inviteMariage(invitation_deja_utiliser.getIdinvite())
                .id_programmaeMariage(invitation_deja_utiliser.getId())
                .build();

        PresenceInvite presenceInvite = PresentInviteDto.formEntity(presentInviteDto);
        presenceInviteRepositiry.save(presenceInvite);
        //fin presence

        Invitations invitations = InvitationDto.fromDto(invitation_deja_utiliser);
        repository.save(invitations);
        return invitation_deja_utiliser;
    }

    //la methode qui va nous permettre de generer le code secre d'urgence
    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10001); // Génère un nombre aléatoire entre 0 et 10000
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

};
