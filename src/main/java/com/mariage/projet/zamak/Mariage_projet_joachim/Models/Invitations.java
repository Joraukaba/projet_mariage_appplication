package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "id_cat")
    private CategorieInvite categorie;

    @OneToOne
    @JoinColumn(name = "id_invite")
    private InviteMariage inviteMariage;

    private String codemariage;

    @OneToOne
    @JoinColumn(name = "id_type_invitation")
    private TypeInvitation typeinvitation;

    private String codeInvitation;

    private boolean validiteInvitation;
}
