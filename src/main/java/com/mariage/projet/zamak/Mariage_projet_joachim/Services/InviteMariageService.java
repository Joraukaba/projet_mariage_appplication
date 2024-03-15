package com.mariage.projet.zamak.Mariage_projet_joachim.Services;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.InviteMariageDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;

import java.util.List;
import java.util.Optional;

public interface InviteMariageService extends AbstractService<InviteMariageDto>{

  Integer valideteInvite(Integer id);

  List<InviteMariageDto>searchInviteMariage(String keyword);




}
