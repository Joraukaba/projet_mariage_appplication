package com.mariage.projet.zamak.Mariage_projet_joachim.Controlers;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.ProgrammeDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.ProgrammeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmes")
@RequiredArgsConstructor
public class ProgrammeMariageControler {

    private final ProgrammeService services;

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody ProgrammeDto dto){
        return  ResponseEntity.ok(services.save(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProgrammeDto>>findAll(){
        return  ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProgrammeDto>findById(@PathVariable("code") String code){
        return  ResponseEntity.ok(services.findbycodemariage(code));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete_code(@PathVariable("code") Integer id){
        services.delete_code(id);
        return ResponseEntity.accepted().build();
    }



}
