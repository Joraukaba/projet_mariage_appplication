package com.mariage.projet.zamak.Mariage_projet_joachim.Services;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;

public interface InviteMariageService extends AbstractService<InviteMariageDto>{

  Integer valideteInvite(Integer id);
}
