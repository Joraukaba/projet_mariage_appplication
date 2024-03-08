package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.SemaineJour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SemaineJourDto {

    private Integer id;

    private String description;

    public static  SemaineJourDto formEntity(SemaineJour semaineJour){
        return SemaineJourDto.builder()
                .id(semaineJour.getId())
                .description(semaineJour.getDescription())
                .build();
    }

    public static SemaineJour fromDto(SemaineJourDto dto){
        return SemaineJour.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .build();
    }
}
