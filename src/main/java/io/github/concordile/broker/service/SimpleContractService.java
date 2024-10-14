/*
 * Copyright 2023-2024 The Concordile Authors
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

package io.github.concordile.broker.service;

import io.github.concordile.broker.domain.Consumer;
import io.github.concordile.broker.domain.Contract;
import io.github.concordile.broker.domain.NewConsumer;
import io.github.concordile.broker.domain.NewContract;
import io.github.concordile.broker.domain.NewProducer;
import io.github.concordile.broker.domain.Producer;
import io.github.concordile.broker.entity.ContractEntity;
import io.github.concordile.broker.repository.ContractRepository;
import io.github.concordile.broker.mapper.entity.ContractEntityMapper;
import io.github.concordile.broker.mapper.simple.NewContract2NewConsumerMapper;
import io.github.concordile.broker.mapper.simple.NewContract2NewProducerMapper;
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
