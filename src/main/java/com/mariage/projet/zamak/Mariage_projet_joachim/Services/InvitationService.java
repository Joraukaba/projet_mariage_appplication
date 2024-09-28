package com.mariage.projet.zamak.Mariage_projet_joachim.Services;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InvitationDto;


import java.util.List;
import java.util.Optional;

public interface InvitationService extends AbstractService<InvitationDto>{

    //InvitationDto findByInviteId(Integer id);
    List<InvitationDto> finAllByInvite(Integer id);

    String invalidateInvitation(String code);

    InvitationDto findByCodeInvitation(String code);

    InvitationDto findByCodeSecret(Integer code_secret);


}
