package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitations, Integer> {
    Optional<Invitations> findByCodeInvitation(String iban);

    Optional<Invitations> findByInviteMariageId(Integer id);

    List<Invitations> findAllByInviteMariageId(Integer id);

    @Query("from Invitations i where i.codeInvitation = :code and i.validiteInvitation=true")
    Optional<Invitations>findByCodeInvitationVerifier(@Param("code") String code);


}
