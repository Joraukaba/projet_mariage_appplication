package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.PresenceInviteRepositiry;
import com.mariage.projet.zamak.Mariage_projet_joachim.Services.PresenceInviteService;
import com.mariage.projet.zamak.Mariage_projet_joachim.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PresenceInviteServiceImpl implements PresenceInviteService {

    private final PresenceInviteRepositiry repositiry;

    private final ObjectsValidator<PresentInviteDto>validator;

    /**
     * @param dto
     * @return
     */
    @Override
    public Integer save(PresentInviteDto dto) {
        validator.validate(dto);
        PresenceInvite presenceInvite = PresentInviteDto.formEntity(dto);
        return repositiry.save(presenceInvite).getId();
    }

    /**
     * @return
     */
    @Override
    public List<PresentInviteDto> findAll() {
        return repositiry.findAll().stream().map(
                PresentInviteDto::formDto
        ).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PresentInviteDto findById(Integer id) {
        return repositiry.findById(id).map(
                PresentInviteDto::formDto
        ).orElseThrow(
                ()-> new EntityNotFoundException("presence non trouve")
        );
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        if(id==null){
            throw new EntityNotFoundException("presence non trouve");
        }
        repositiry.deleteById(id);
    }
}
