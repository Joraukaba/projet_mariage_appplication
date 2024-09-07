package com.mariage.projet.zamak.Mariage_projet_joachim.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MouvementInvite {

    @Id
    @GeneratedValue
    private Integer id;
    private  String coderfid;
}
