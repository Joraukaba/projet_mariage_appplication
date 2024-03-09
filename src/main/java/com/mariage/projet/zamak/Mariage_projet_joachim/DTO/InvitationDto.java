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

    public static  InvitationDto fromEntity(Invitations invitations){
        return InvitationDto.builder()
                .id(invitations.getId())
                .idinvite(invitations.getInviteMariage().getId())
                .codemariage(invitations.getCodemariage())
                .codeInvitation(invitations.getCodeInvitation())
                .validiteInvitation(invitations.isValiditeInvitation())
                .build();
    }

    public static Invitations fromDto(InvitationDto dto){
        return Invitations.builder()
                .id(dto.getId())
                .inviteMariage(InviteMariage.builder()
                        .id(dto.getIdinvite())
                        .build())
                .codemariage(dto.getCodemariage())
                .codeInvitation(dto.codeInvitation)
                .validiteInvitation(dto.isValiditeInvitation())
                .build();
    }
}

