package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PresenceInviteRepositiry extends JpaRepository<PresenceInvite, Integer> {
  Optional<PresenceInvite>findById(Integer id);
  @Query("SELECT p FROM PresenceInvite p JOIN FETCH p.inviteMariage JOIN FETCH p.programmaeMariage")
  List<PresenceInvite> findAllWithInviteAndProgramme();
}
