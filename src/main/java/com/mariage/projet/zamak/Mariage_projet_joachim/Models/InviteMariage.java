package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@CrossOrigin("*")
public class InviteMariage {

    @Id
    @GeneratedValue
    private Integer id;

    private String nomComplete;

    @ManyToOne()
    private ProgrammaeMariage programme;

    @ManyToOne()
    private CategorieInvite categorieInvite;

    @ManyToOne()
    private TypeInvitation typeInvitation;

    @OneToMany(mappedBy = "inviteMariage")
    private List<Invitations> invitations;

}
