package com.mariage.projet.zamak.Mariage_projet_joachim.Controlers;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.DetailMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InviteMariageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invites")
@RequiredArgsConstructor
public class InviteMariageControler {

    private final InviteMariageService services;


    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody InviteMariageDto dto){
        return ResponseEntity.ok(services.save(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<InviteMariageDto>>findAll(){
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InviteMariageDto>findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(services.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete( @PathVariable("id") Integer id){
        services.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/validate/{id_invite}")
    public ResponseEntity<Integer>validateInvite( @PathVariable("id_invite") Integer id){
        return ResponseEntity.ok(services.valideteInvite(id));
    }



}
