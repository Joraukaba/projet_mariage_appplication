package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
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

    /**
     * ici nous allons faire une rechercher d'une invitation par son code
     * */
    @Override
    public InvitationDto findByCodeInvitation(String code) {

        // Vérifier si le code de l'invitation existe dans la table Invitations
        InvitationDto invitationDejaUtilisee = repository.findByCodeInvitationVerifier(code)
                .map(InvitationDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Invitation déjà utilisée ou n'existe pas dans la base des données"));

        // Marquer l'invitation comme non valide
        invitationDejaUtilisee.setValiditeInvitation(false);

        // Récupérer l'invité mariage à partir de l'invitation
        InviteMariage inviteMariage = inviteRepositorisl.findById(invitationDejaUtilisee.getIdinvite())
                .orElseThrow(() -> new EntityNotFoundException("Invité non trouvé"));

        // Récupérer le programme mariage à partir de l'invitation
        ProgrammaeMariage programmaeMariage = programmeRepository.findById(invitationDejaUtilisee.getProgrammaeMariage())
                .orElseThrow(() -> new EntityNotFoundException("Programme Mariage non trouvé"));

        // Créer l'objet PresenceInvite
        PresentInviteDto presentInviteDto = PresentInviteDto.builder()
                .statut(true)
                .date(LocalDateTime.now())
                .inviteMariage(inviteMariage.getNomComplete())  // Utiliser l'invité existant
                .id_programmaeMariage(programmaeMariage.getId())  // Utiliser le programme existant
                .build();

        // Convertir le DTO en entité PresenceInvite avec les entités récupérées
        PresenceInvite presenceInvite = PresentInviteDto.formEntity(presentInviteDto, inviteMariage, programmaeMariage);

        // Enregistrer la présence de l'invité
        presenceInviteRepositiry.save(presenceInvite);

        // Mettre à jour et sauvegarder l'invitation
        Invitations invitations = InvitationDto.fromDto(invitationDejaUtilisee);
        repository.save(invitations);

        return invitationDejaUtilisee;
    }




    /**
     * @param code_secret
     * @return une invitation avec son soce secret de l'invitation
     */
    @Override
    public InvitationDto findByCodeSecret(Integer code_secret) {
//        //chercher l'invitation exist dans la base des donnees
//        InvitationDto invitationbycode = repository.findByCodeSecretVerifier(code_secret).map(
//                InvitationDto::fromEntity
//        ).orElseThrow(
//                ()-> new EntityNotFoundException("cette invitation avec code "+code_secret+" ne pas dans la base des données ou deja utiliser")
//        );
//        //changer l'etat de l'invitation en false
//        invitationbycode.setValiditeInvitation(false);
//
//        //generer une present pour l'inviter
//        PresentInviteDto presentInviteDto = PresentInviteDto.builder()
//                .statut(true)
//                .date(LocalDateTime.now())
//                .inviteMariage(invitationbycode.getNomCompletinv())
//                .id_programmaeMariage(invitationbycode.getProgrammaeMariage())
//                .build();
//
//        PresenceInvite presenceInvite = PresentInviteDto.formEntity(presentInviteDto);
//
//        presenceInviteRepositiry.save(presenceInvite);
//
//        Invitations invitations = InvitationDto.fromDto(invitationbycode);
//        repository.save(invitations);
//        return invitationbycode;
        return  null;
    }

    /**
     * @param
     * @return une invitation avec son id de la carte magnetique
     */

    //la methode qui va nous permettre de generer le code secre d'urgence pour les sms
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
    
    private String generateIdCarte(){
        String[] ElementCarteId = {"DB 9W 873 92", "54K 34 23 10"};

        return  null;
    }

};
