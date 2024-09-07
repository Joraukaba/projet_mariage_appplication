package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.MouvementInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MouvementInviteRepository extends JpaRepository<MouvementInvite, Integer> {

    @Query("from  MouvementInvite m where m.coderfid =:code")
    Optional<MouvementInvite>findByCoderfid(@Param("code") String code);
}
