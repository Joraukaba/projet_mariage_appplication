package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PresentInviteDto {

    private Integer id;

    private Boolean statut;

    private LocalDateTime date;

    private Integer inviteMariage;

    public static PresentInviteDto formDto(PresenceInvite presenceInvite){
        return PresentInviteDto.builder()
                .id(presenceInvite.getId())
                .statut(presenceInvite.getStatut())
                .date(presenceInvite.getDate())
                .inviteMariage(presenceInvite.getInviteMariage().getId())
                .build();
    }

    public static PresenceInvite formEntity(PresentInviteDto dto){
        return PresenceInvite.builder()
                .id(dto.getId())
                .statut(dto.getStatut())
                .date(dto.getDate())
                .inviteMariage(
                   InviteMariage.builder()
                           .id(dto.getInviteMariage())
                           .build()
                )
                .build();
    }
}
