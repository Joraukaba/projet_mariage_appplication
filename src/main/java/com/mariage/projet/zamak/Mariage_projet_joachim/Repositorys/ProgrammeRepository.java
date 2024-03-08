package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.ProgrammeDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgrammeRepository extends JpaRepository<ProgrammaeMariage, Integer> {
    Optional<ProgrammaeMariage>findByCodeMariage(String code);




}
