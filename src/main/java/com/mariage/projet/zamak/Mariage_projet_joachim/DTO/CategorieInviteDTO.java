package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.CategorieInvite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CategorieInviteDTO {

    private Integer id;

    private String libelle;

    public static CategorieInviteDTO fromEntity(CategorieInvite categorieInvite){
        return CategorieInviteDTO.builder()
                .id(categorieInvite.getId())
                .libelle(categorieInvite.getLibelle())
                .build();
    }

    public static CategorieInvite formDto(CategorieInviteDTO dto){
        return CategorieInvite.builder()
                .id(dto.getId())
                .libelle(dto.getLibelle())
                .build();
    }
}
