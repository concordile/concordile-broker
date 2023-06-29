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
import io.github.projectleopold.domain.NewConsumer;
import io.github.projectleopold.domain.Producer;
import io.github.projectleopold.entity.ConsumerEntity;
import io.github.projectleopold.mapper.entity.ConsumerEntityMapper;
import io.github.projectleopold.mapper.domain.NewConsumerDomainMapper;
import io.github.projectleopold.mapper.entity.ProducerEntityMapper;
import io.github.projectleopold.repository.ConsumerRepository;
import io.github.projectleopold.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleConsumerService implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final ProducerRepository producerRepository;

    private final NewConsumerDomainMapper newConsumerDomainMapper;
    private final ConsumerEntityMapper consumerEntityMapper;
    private final ProducerEntityMapper producerEntityMapper;

    @Override
    public Consumer createConsumer(NewConsumer newConsumer) {
        ConsumerEntity entity = newConsumerDomainMapper.mapDomainToEntity(newConsumer);
        ConsumerEntity result = consumerRepository.save(entity);
        return consumerEntityMapper.mapEntityToDomain(result);
    }

    @Override
    public Optional<Consumer> findConsumer(String consumerName) {
        return consumerRepository.findByName(consumerName)
                .map(consumerEntityMapper::mapEntityToDomain);
    }

    @Override
    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll().stream()
                .map(consumerEntityMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producer> getAllProducers(String consumerName) {
        return producerRepository.findAllByConsumerName(consumerName).stream()
                .map(producerEntityMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

}
