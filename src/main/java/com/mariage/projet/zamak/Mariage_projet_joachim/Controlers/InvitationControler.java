package com.mariage.projet.zamak.Mariage_projet_joachim.Controlers;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.DetailMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InvitationService;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.InviteMariageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitations")
@RequiredArgsConstructor
@CrossOrigin("*")

public class InvitationControler {

    private final InvitationService services;


    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody InvitationDto dto){
        return ResponseEntity.ok(services.save(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<InvitationDto>>findAll(){
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvitationDto>findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(services.findById(id));
    }

    @GetMapping("/invite/{id}")
    public ResponseEntity<InvitationDto>findByInviteMariageId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(services.findByInviteId(id));
    }

    @GetMapping("/info_invite/{id}")
    public ResponseEntity<List<InvitationDto>>findAllByInviteId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(services.finAllByInvite(id));
    }


    @PatchMapping("/invalide/{code}")
    public ResponseEntity<String>invilidateInvitation(@PathVariable("code") String code){
        return ResponseEntity.ok(services.invalidateInvitation(code));
    }

    @PatchMapping("/control_invitation/{code}")
    public ResponseEntity<InvitationDto>findByCodeInvation(@PathVariable("code") String code){
        return ResponseEntity.ok(services.findByCodeInvitation(code));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete( @PathVariable("id") Integer id){
        services.delete(id);
        return ResponseEntity.accepted().build();
    }




}
