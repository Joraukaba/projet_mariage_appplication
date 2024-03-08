package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

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
public class TypeInvitationDto {
    private Integer id;

    private String description;

    public static TypeInvitationDto fromEntity(TypeInvitation typeInvitation){
        return TypeInvitationDto.builder()
                .id(typeInvitation.getId())
                .description(typeInvitation.getDescription())
                .build();
    }

    public static TypeInvitation fromDto(TypeInvitationDto typeInvitation){
        return TypeInvitation.builder()
                .id(typeInvitation.getId())
                .description(typeInvitation.getDescription())
                .build();
    }
}
