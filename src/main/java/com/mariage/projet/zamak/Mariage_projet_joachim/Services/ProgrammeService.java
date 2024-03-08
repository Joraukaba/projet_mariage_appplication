package com.mariage.projet.zamak.Mariage_projet_joachim.Services;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.ProgrammeDto;

import java.util.List;

public interface ProgrammeService  {
    String save( ProgrammeDto dto);
    ProgrammeDto findbycodemariage( String code);

    List<ProgrammeDto> findAll();

    void delete_code(Integer id);
}
