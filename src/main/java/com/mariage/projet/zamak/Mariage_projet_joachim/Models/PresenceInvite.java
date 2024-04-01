package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PresenceInvite {
    @Id
    @GeneratedValue
    private Integer id;

    private Boolean statut;

    private LocalDateTime date;

    @OneToOne()
    @JoinColumn(name = "id_invite")
    private InviteMariage inviteMariage;

    @ManyToOne
    @JoinColumn(name = "id_programm")
    private ProgrammaeMariage programmaeMariage;



}
