package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.CategorieInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.TypeInvitation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InvitationDto {

    private Integer id;

    private Integer idinvite;

    private String codemariage;
    private String codeInvitation;

    private boolean validiteInvitation;

    private Integer typeinvite;

    private Integer categorieinvite;

    private String nomCompletinv;

    private String categorieinv;

    private String typeinv;

    public static  InvitationDto fromEntity(Invitations invitations){
        return InvitationDto.builder()
                .id(invitations.getId())
                .idinvite(invitations.getInviteMariage().getId())
                .codemariage(invitations.getCodemariage())
                .codeInvitation(invitations.getCodeInvitation())
                .validiteInvitation(invitations.isValiditeInvitation())
                .typeinvite(invitations.getTypeInvitation().getId())
                .categorieinvite(invitations.getCategorieInvite().getId())
                .typeinv(invitations.getTypeInvitation().getDescription())
                .categorieinv(invitations.getCategorieInvite().getLibelle())
                .nomCompletinv(invitations.getNomCompletinv())
                .build();
    }

    public static Invitations fromDto(InvitationDto dto){
        return Invitations.builder()
                .id(dto.getId())
                .inviteMariage(InviteMariage.builder()
                        .id(dto.getIdinvite())
                        .build())
                .typeInvitation(
                        TypeInvitation.builder()
                                .id(dto.getTypeinvite())
                                .build()
                )
                .categorieInvite(
                        CategorieInvite.builder()
                                .id(dto.getCategorieinvite())
                                .build()
                )
                .nomCompletinv(dto.getNomCompletinv())
                .typeinv(dto.getTypeinv())
                .categorieinv(dto.getCategorieinv())
                .codemariage(dto.getCodemariage())
                .codeInvitation(dto.getCodeInvitation())
                .validiteInvitation(dto.isValiditeInvitation())
                .build();
    }
}

