package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitations, Integer> {
    Optional<Invitations> findByCodeInvitation(String iban);

    @Query("from Invitations i where i.inviteMariage.id=:id AND i.validiteInvitation=true")
    Optional<Boolean> findByInviteMariageIdvalide(@Param("id") Integer id);

    @Query("from Invitations i where i.inviteMariage.id=:id")
    Optional<Invitations>findByInviteMariageId( @Param("id") Integer id);

    List<Invitations> findAllByInviteMariageId(Integer id);

    @Query("from Invitations i where i.codeInvitation = :code and i.validiteInvitation=true")
    Optional<Invitations>findByCodeInvitationVerifier(@Param("code") String code);

    //ici il faut mettre la requetes pour faire une rechercher de l'invitation par son code secret

    @Query("from Invitations  i where i.codesecret = :code and i.validiteInvitation=true")
    Optional<Invitations>findByCodeSecretVerifier(@Param("code") Integer code);
    //ici aussi il faut implementer une requete pour chercher une invitation par carte magnetique

    @Query("from Invitations  i where i.cartemagnetique = :carte and i.validiteInvitation =true")
    Optional<Invitations>findByCartemagnetiqueVerifier(@Param("carte") String carte);



}
