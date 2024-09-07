package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Invitations {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_invite")
    private InviteMariage inviteMariage;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TypeInvitation typeInvitation;

    @ManyToOne
    @JoinColumn(name="id_cat")
    private CategorieInvite categorieInvite;

    private String codemariage;
    private String nomCompletinv;
    private String categorieinv;
    private String cartemagnetique;
    private String typeinv;
    private String codeInvitation;
    private boolean validiteInvitation;
    private Integer codesecret;

    @ManyToOne
    @JoinColumn(name = "id_program")
    private ProgrammaeMariage programmaeMariage;
}
