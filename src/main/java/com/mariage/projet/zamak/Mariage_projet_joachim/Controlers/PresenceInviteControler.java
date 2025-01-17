package com.mariage.projet.zamak.Mariage_projet_joachim.Controlers;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.CategorieInviteDTO;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.PresenceInviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presences")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PresenceInviteControler {
    private final PresenceInviteService services;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody PresentInviteDto dto){
        return ResponseEntity.ok(services.save(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<PresentInviteDto>>findAll(){
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresentInviteDto>findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(services.findById(id));
    }

    @GetMapping("/rapport/")
    public ResponseEntity<List<PresentInviteDto>> findAllWithInviteAndProgramme(){
        return  ResponseEntity.ok(services.findAllWithInviteAndProgramme());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete( @PathVariable("id") Integer id){
        services.delete(id);
        return ResponseEntity.accepted().build();
    }

}
