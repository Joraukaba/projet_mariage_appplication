package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PresenceInviteRepositiry extends JpaRepository<PresenceInvite, Integer> {
  Optional<PresenceInvite>findById(Integer id);
}
