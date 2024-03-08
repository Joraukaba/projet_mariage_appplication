package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ProgrammaeMariage {

    @Id
    @GeneratedValue
    private Integer id;

    private  String codeMariage;

    private String famille;

    private LocalDate date;

    private Integer nombreInvite;

    @OneToMany(mappedBy = "programmaeMariage")
    private List<DetailMariage> detailMariages;

    @OneToMany(mappedBy = "programme")
    List<InviteMariage>inviteMariages;


}
