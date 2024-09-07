package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.ProgrammeDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProgrammeRepository extends JpaRepository<ProgrammaeMariage, Integer> {
    Optional<ProgrammaeMariage>findByCodeMariage(String code);

    //cette requette nous permet de faire la rechercher des programmes par nom de la famille
    @Query("from ProgrammaeMariage p where p.famille like :kw")
    List<ProgrammaeMariage>searchProgrammaeMariageByFamille(@Param("kw") String kyword);


    @Query("select count(i.nomComplete) from  InviteMariage  i inner join ProgrammaeMariage p on i.programme.id=p.id where p.id=:idp")
    Integer nombreTotalInvite(@Param("idp") Integer id);




}
