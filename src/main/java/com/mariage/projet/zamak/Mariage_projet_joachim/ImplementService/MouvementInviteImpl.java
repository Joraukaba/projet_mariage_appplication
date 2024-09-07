package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.MouvementInviteDTO;
import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.MouvementInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.MouvementInviteRepository;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.MouvementInviteService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MouvementInviteImpl implements MouvementInviteService {

    private final MouvementInviteRepository repository;

    private final ObjectsValidator<MouvementInviteDTO> validator;

    /**
     * @param dto
     * @return
     */
    @Override
    public Integer save(MouvementInviteDTO dto) {
        validator.validate(dto);
        MouvementInvite mouvementInvite  = MouvementInviteDTO.formEntity(dto);
        return repository.save(mouvementInvite).getId();
    }

    /**
     * @return
     */
    @Override
    public List<MouvementInviteDTO> findAll() {
        return repository.findAll().stream().map(
                MouvementInviteDTO::formDto
        ).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MouvementInviteDTO findById(Integer id) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {

    }

    /**
     * @param coderfid
     * @return
     */
    @Override
    public MouvementInviteDTO verificationRfig(String coderfid) {
     return repository.findByCoderfid(coderfid).map(
             MouvementInviteDTO::formDto
     ).orElseThrow(
             ()-> new EntityNotFoundException("Je ne trouve pas le code RFID")
     );
    }
}
