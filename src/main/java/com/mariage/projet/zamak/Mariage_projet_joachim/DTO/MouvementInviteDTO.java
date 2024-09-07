package com.mariage.projet.zamak.Mariage_projet_joachim.DTO;

import com.mariage.projet.zamak.Mariage_projet_joachim.Models.MouvementInvite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MouvementInviteDTO {
    private  Integer id;
    private String coderfiddto;

    public static MouvementInviteDTO formDto(MouvementInvite mouvementInvite){
        return MouvementInviteDTO.builder()
                .id(mouvementInvite.getId())
                .coderfiddto(mouvementInvite.getCoderfid())
                .build();

    }

    public static  MouvementInvite formEntity(MouvementInviteDTO dto){
        return MouvementInvite.builder()
                .id(dto.id)
                .coderfid(dto.coderfiddto)
                .build();

    }}
