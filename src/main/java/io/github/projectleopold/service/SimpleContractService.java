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

import io.github.projectleopold.domain.Consumer;
import io.github.projectleopold.domain.Contract;
import io.github.projectleopold.domain.NewConsumer;
import io.github.projectleopold.domain.NewContract;
import io.github.projectleopold.domain.NewProducer;
import io.github.projectleopold.domain.Producer;
import io.github.projectleopold.entity.ContractEntity;
import io.github.projectleopold.mapper.ContractEntityMapper;
import io.github.projectleopold.mapper.NewContract2NewConsumerMapper;
import io.github.projectleopold.mapper.NewContract2NewProducerMapper;
import io.github.projectleopold.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SimpleContractService implements ContractService {

    private final ProducerService producerService;
    private final ConsumerService consumerService;

    private final NewContract2NewProducerMapper newContract2NewProducerMapper;
    private final NewContract2NewConsumerMapper newContract2NewConsumerMapper;
    private final ContractEntityMapper contractEntityMapper;

    private final ContractRepository contractRepository;

    @Override
    public Contract createContract(NewContract newContract) {
        NewProducer newProducer = newContract2NewProducerMapper.map(newContract);
        Producer producer = producerService.createProducer(newProducer);
        NewConsumer newConsumer = newContract2NewConsumerMapper.map(newContract);
        Consumer consumer = consumerService.createConsumer(newConsumer);
        //TODO: Move mapping
        ContractEntity entity = ContractEntity.builder()
                .id(UUID.randomUUID().toString())
                .producer(producer.getName())
                .consumer(consumer.getName())
                .build();
        ContractEntity result = contractRepository.save(entity);
        return contractEntityMapper.mapEntityToDomain(result);
    }

}
