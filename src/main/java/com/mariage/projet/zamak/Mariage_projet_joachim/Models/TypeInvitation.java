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
public class TypeInvitation {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @OneToMany(mappedBy = "typeInvitation")
    private List<InviteMariage> inviteMariage;

    @OneToMany(mappedBy = "typeInvitation")
    private List<Invitations> invitations;
}
