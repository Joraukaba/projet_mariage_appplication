package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
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

    private String inviteMariage;

    private String inviteFamille;  // Ajouté

    private Integer id_programmaeMariage;

    public static PresentInviteDto formDto(PresenceInvite presenceInvite){
        return PresentInviteDto.builder()
                .id(presenceInvite.getId())
                .statut(presenceInvite.getStatut())
                .date(presenceInvite.getDate())
                .inviteMariage(presenceInvite.getInviteMariage().getNomComplete())
                .inviteFamille(presenceInvite.getProgrammaeMariage().getFamille())
                .id_programmaeMariage(presenceInvite.getProgrammaeMariage().getId())

                .build();
    }

    public static PresenceInvite formEntity(PresentInviteDto dto, InviteMariage inviteMariage, ProgrammaeMariage programmaeMariage) {
        return PresenceInvite.builder()
                .id(dto.getId())
                .statut(dto.getStatut())
                .date(dto.getDate())
                .inviteMariage(inviteMariage)  // Utiliser l'invité existant
                .programmaeMariage(programmaeMariage)  // Utiliser le programme existant
                .build();
    }



}
