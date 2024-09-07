package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.ProgrammeDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.ProgrammeRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.ProgrammeService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProgrammationServiceImpl implements ProgrammeService  {

    private final ProgrammeRepository repository;
    private final ObjectsValidator<ProgrammeDto>validator;


    /**
     * @param
     * @return
     */


    @Override
    public String save(ProgrammeDto dto) {
        validator.validate(dto);
        ProgrammaeMariage programme = ProgrammeDto.formDto(dto);
        programme.setCodeMariage(generateRandomCodeProgramme());
        return repository.save(programme).getCodeMariage();
    }

    @Override
    public ProgrammeDto findbycodemariage(String code) {
        return repository.findByCodeMariage(code).map(
                ProgrammeDto::fromEntity
        ).orElseThrow(
                ()-> new EntityNotFoundException("le programme avec code  <<"+ code+" >> que vous chercher n'existe")
        );
    }

    @Override
    public List<ProgrammeDto> findAll() {
        return repository.findAll().stream().map(
                ProgrammeDto::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public void delete_code(Integer id) {
        if(id==null){
            throw  new EntityNotFoundException("Programme non trouve");
        }
        repository.deleteById(id);

    }

    /** methode pour faire la chercher des programmes par la famille*/
    @Override
    public List<ProgrammeDto> searchProgramme(String keyword) {
        return repository.searchProgrammaeMariageByFamille(keyword).stream().map(
                ProgrammeDto::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public Integer NumberInviters(Integer id) {
        return repository.nombreTotalInvite(id);
    }


    //methode pour generere les codes de programme mariage
    private String generateRandomCodeProgramme() {
        // generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        // check if the iban already exists
        boolean codeMariageExists = repository.findByCodeMariage(iban).isPresent();
        // if exists -> generate new random iban
        if (codeMariageExists) {
            generateRandomCodeProgramme();
        }
        // if not exist -> return generated iban
        return iban;
    }

}
