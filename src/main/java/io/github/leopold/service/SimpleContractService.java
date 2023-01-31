package io.github.leopold.service;

import io.github.leopold.domain.Contract;
import io.github.leopold.domain.NewContract;
import io.github.leopold.entity.ContractEntity;
import io.github.leopold.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleContractService implements ContractService {

    private final ParticipantService participantService;
    private final ContractRepository contractRepository;

    @Override
    public Contract createContract(NewContract domain) {
        //TODO: Move mapping
        String producerName = domain.getProducer();
        participantService.createParticipant(producerName);
        String consumerName = domain.getConsumer();
        participantService.createParticipant(consumerName);
        ContractEntity entity = ContractEntity.builder()
                .id(UUID.randomUUID().toString())
                .producer(producerName)
                .consumer(consumerName)
                .build();
        ContractEntity result = contractRepository.save(entity);
        return Contract.builder()
                .id(result.getId())
                .producer(result.getProducer())
                .consumer(result.getConsumer())
                .build();
    }

}
