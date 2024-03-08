package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class InviteMariage {

    @Id
    @GeneratedValue
    private Integer id;

    private String nomComplete;

    @ManyToOne()
    @JoinColumn(name = "id_programme")
    private ProgrammaeMariage programme;

    @OneToOne()
    @JoinColumn(name = "id_cat")
    private CategorieInvite categorieInvite;

    @OneToOne()
    @JoinColumn(name = "id_type")
    private TypeInvitation typeInvitation;




}
