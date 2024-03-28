package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.DetailMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammeDto {

    private  Integer id;

    private  String code;

    private String famille;

    private Integer NombreInvite;


    private LocalDate date ;

    public static  ProgrammeDto fromEntity(ProgrammaeMariage programme){
        return ProgrammeDto.builder()
                .id(programme.getId())
                .code(programme.getCodeMariage())
                .famille(programme.getFamille())
                .date(programme.getDate())
                .NombreInvite(programme.getNombreInvite())
                .build();
    }


    public static ProgrammaeMariage formDto(ProgrammeDto dto){
        return  ProgrammaeMariage.builder()
                .id(dto.getId())
                .codeMariage(dto.getCode())
                .famille(dto.getFamille())
                .date(dto.getDate())
                .nombreInvite(dto.getNombreInvite())
                .build();
    }

}
