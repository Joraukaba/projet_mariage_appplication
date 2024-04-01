package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@CrossOrigin("*")
public class CategorieInvite {
    @Id
    @GeneratedValue
    private Integer id;

    private String libelle;

    @OneToMany(mappedBy = "categorieInvite")
    private List<InviteMariage> inviteMariage;

    @OneToMany(mappedBy = "categorieInvite")
    private List<Invitations>invitations;

}
