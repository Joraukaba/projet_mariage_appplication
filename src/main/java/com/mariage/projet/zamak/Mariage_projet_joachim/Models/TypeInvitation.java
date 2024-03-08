package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne()
    private InviteMariage inviteMariage;
}
