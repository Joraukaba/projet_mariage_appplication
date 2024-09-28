package com.mariage.projet.zamak.Mariage_projet_joachim.ImplementService;

import com.mariage.projet.zamak.Mariage_projet_joachim.DTO.PresentInviteDto;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.InviteMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.PresenceInvite;
import com.mariage.projet.zamak.Mariage_projet_joachim.Models.ProgrammaeMariage;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.InviteRepositoris;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.PresenceInviteRepositiry;
import com.mariage.projet.zamak.Mariage_projet_joachim.Repositorys.ProgrammeRepository;
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

    private final InviteRepositoris inviteRepositoris;

    private final ProgrammeRepository programmeRepository;

    /**
     * @param dto
     * @return
     */
    @Override
    public Integer save(PresentInviteDto dto) {
        // Charger l'invité et le programme à partir de la base de données
        InviteMariage inviteMariage = inviteRepositoris.findByNomComplete(dto.getInviteMariage())
                .orElseThrow(() -> new EntityNotFoundException("Invite non trouvé"));

        ProgrammaeMariage programmaeMariage = programmeRepository.findById(dto.getId_programmaeMariage())
                .orElseThrow(() -> new EntityNotFoundException("Programme non trouvé"));

        // Créer l'entité PresenceInvite en utilisant les entités chargées
        PresenceInvite presenceInvite = PresentInviteDto.formEntity(dto, inviteMariage, programmaeMariage);

        // Enregistrer la présence
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


    /**
     * @return
     */
    @Override
    public List<PresentInviteDto> findAllWithInviteAndProgramme() {
        List<PresenceInvite> invites = repositiry.findAllWithInviteAndProgramme();
        return invites.stream()
                .map(PresentInviteDto::formDto)
                .collect(Collectors.toList());
    }
}
