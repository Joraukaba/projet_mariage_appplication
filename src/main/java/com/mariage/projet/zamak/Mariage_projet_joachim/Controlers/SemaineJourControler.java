package com.mariage.projet.zamak.Mariage_projet_joachim.Controlers;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.DetailMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.SemaineJourDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.SemaineJourService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semaines")
@RequiredArgsConstructor
public class SemaineJourControler {

    private final SemaineJourService services;

    private final ObjectsValidator<SemaineJourDto>validator;


    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody SemaineJourDto dto){
        return ResponseEntity.ok(services.save(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<SemaineJourDto>>findAll(){
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemaineJourDto>findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(services.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete( @PathVariable("id") Integer id){
        services.delete(id);
        return ResponseEntity.accepted().build();
    }

}
