/*
 * Copyright 2023 The Project Leopold Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.projectleopold.service;

import io.github.projectleopold.domain.Contract;
import io.github.projectleopold.domain.NewContract;
import io.github.projectleopold.entity.ContractEntity;
import io.github.projectleopold.repository.ContractRepository;
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
