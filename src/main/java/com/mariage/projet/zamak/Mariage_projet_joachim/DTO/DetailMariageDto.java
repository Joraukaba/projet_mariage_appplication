package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.DetailMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.SemaineJour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DetailMariageDto {

    private Integer id;

    private String programme;

    private LocalDate date;

    private String heure;

    private Integer id_programmaeMariage;

    private Integer id_semaineJour;


    public static DetailMariageDto fromEntity(DetailMariage detailMariage){
        return DetailMariageDto.builder()
                .id(detailMariage.getId())
                .programme(detailMariage.getProgramme())
                .date(detailMariage.getDate())
                .id_programmaeMariage(detailMariage.getProgrammaeMariage().getId())
                .id_semaineJour(detailMariage.getSemaineJour().getId())
                .build();
    }

    public static DetailMariage fromDto(DetailMariageDto dto){
        return DetailMariage.builder()
                .id(dto.getId())
                .programme(dto.getProgramme())
                .date(dto.getDate())
                .programmaeMariage(
                        ProgrammaeMariage.builder()
                                .id(

                                        dto.getId_programmaeMariage()
                                )
                                .build()
                )
                .semaineJour(
                        SemaineJour.builder()
                                .id(dto.getId_semaineJour())
                                .build()
                )
                .build();
    }
}
