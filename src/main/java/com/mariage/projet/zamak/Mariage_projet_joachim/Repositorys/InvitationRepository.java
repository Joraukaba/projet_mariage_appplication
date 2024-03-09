package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitations, Integer> {
    Optional<Invitations> findByCodeInvitation(String iban);

    Optional<Invitations> findByInviteMariageId(Integer id);

    List<Invitations> findAllByInviteMariageId(Integer id);

    Optional<Invitations>findByCodeInvitationIgnoreCase(String code);


}
