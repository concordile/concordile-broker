package io.github.leopold.service;

import io.github.leopold.domain.Participant;
import io.github.leopold.entity.ParticipantEntity;
import io.github.leopold.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleParticipantService implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    public Participant createParticipant(String name) {
        //TODO: Move mapping
        ParticipantEntity entity = ParticipantEntity.builder()
                .name(name)
                .build();
        ParticipantEntity result = participantRepository.save(entity);
        return Participant.builder()
                .name(result.getName())
                .build();
    }

}
