package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;


import com.mariage.projet.zamak.Mariage_projet_joachim.Models.CategorieInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.TypeInvitation;
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
public class InviteMariageDto {

    private Integer id;

    private String nomComplete;

    private Integer id_programme;

    private Integer id_categorieInvite;

    private Integer id_typeInvitation;

    public static InviteMariageDto formEntity(InviteMariage inviteMariage){
        return InviteMariageDto.builder()
                .id(inviteMariage.getId())
                .nomComplete(inviteMariage.getNomComplete())
                .id_programme(inviteMariage.getProgramme().getId())
                .id_categorieInvite(inviteMariage.getCategorieInvite().getId())
                .id_typeInvitation(inviteMariage.getTypeInvitation().getId())
                .build();
    }

    public static InviteMariage formDto(InviteMariageDto dto){
        return InviteMariage.builder()
                .id(dto.getId())
                .nomComplete(dto.getNomComplete())
                .programme(ProgrammaeMariage.builder()
                        .id(dto.getId_programme())
                        .build())
                .categorieInvite(
                        CategorieInvite.builder()
                                .id(dto.getId_categorieInvite())
                                .build()
                )
                .typeInvitation(
                        TypeInvitation.builder()
                                .id(dto.getId_typeInvitation())
                                .build()
                )
                .build();
    }
}
