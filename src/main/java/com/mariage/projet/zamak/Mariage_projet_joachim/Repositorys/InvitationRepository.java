package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitations, Integer> {
    Optional<Invitations> findByCodeInvitation(String iban);

    Optional<Invitations> findByInviteMariageId(Integer id);
}
