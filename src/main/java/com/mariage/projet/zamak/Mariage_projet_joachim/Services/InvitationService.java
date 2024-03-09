package com.mariage.projet.zamak.Mariage_projet_joachim.Services;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.Invitations;

import java.util.List;
import java.util.Optional;

public interface InvitationService extends AbstractService<InvitationDto>{

    InvitationDto findByInviteId(Integer id);
    List<InvitationDto> finAllByInvite(Integer id);

    Integer invalidateInvitation(Integer id);

    InvitationDto findByCodeInvitation(String code);
}
