package com.mariage.projet.zamak.Mariage_projet_joachim.Controlers;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.MouvementInviteDTO;
import com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService.MouvementInviteImpl;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.MouvementInviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rfid")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MouvementInviteControler {

    private final MouvementInviteService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody MouvementInviteDTO dto){
        return ResponseEntity.ok(service.save(dto));

    }

    @GetMapping("/")
    public ResponseEntity<List<MouvementInviteDTO>> findall(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/mvt/{rfid}")
    public ResponseEntity<MouvementInviteDTO>verification(@PathVariable("rfid") String code){
        return ResponseEntity.ok(service.verificationRfig(code));
    }
}
