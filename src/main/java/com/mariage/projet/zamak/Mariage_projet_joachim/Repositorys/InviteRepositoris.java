package com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InviteRepositoris extends JpaRepository<InviteMariage, Integer> {

    @Query("select  p , count (i) as nombreinvite from ProgrammaeMariage p left join p.inviteMariages i group by p")
    List<InviteMariage>nombreInviteParInviter();
     //cette fonction va nous permettre de faire la rechercher des invites

    @Query("from InviteMariage i where i.nomComplete like :kw")
    List<InviteMariage>searchByNomCompleteIgnoreCase(@Param("kw") String keyword);


}
