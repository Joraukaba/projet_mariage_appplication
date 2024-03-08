package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class DetailMariage {

    @Id
    @GeneratedValue
    private Integer id;

    private String programme;

    private LocalDate date;

    private String heure;

    @ManyToOne()
    @JoinColumn(name = "id_programme")
    private ProgrammaeMariage programmaeMariage;

    @ManyToOne()
    @JoinColumn(name = "id_semaine")
    private SemaineJour semaineJour;

}
