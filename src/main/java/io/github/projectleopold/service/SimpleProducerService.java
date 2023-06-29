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
import io.github.projectleopold.domain.NewProducer;
import io.github.projectleopold.domain.Producer;
import io.github.projectleopold.entity.ProducerEntity;
import io.github.projectleopold.mapper.entity.ConsumerEntityMapper;
import io.github.projectleopold.mapper.domain.NewProducerDomainMapper;
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
public class SimpleProducerService implements ProducerService {

    private final ProducerRepository producerRepository;
    private final ConsumerRepository consumerRepository;

    private final NewProducerDomainMapper newProducerDomainMapper;
    private final ProducerEntityMapper producerEntityMapper;
    private final ConsumerEntityMapper consumerEntityMapper;

    @Override
    public Producer createProducer(NewProducer newProducer) {
        ProducerEntity entity = newProducerDomainMapper.mapDomainToEntity(newProducer);
        ProducerEntity result = producerRepository.save(entity);
        return producerEntityMapper.mapEntityToDomain(result);
    }

    @Override
    public Optional<Producer> findProducer(String producerName) {
        return producerRepository.findByName(producerName)
                .map(producerEntityMapper::mapEntityToDomain);
    }

    @Override
    public List<Producer> getAllProducers() {
        return producerRepository.findAll().stream()
                .map(producerEntityMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Consumer> getAllConsumers(String producerName) {
        return consumerRepository.findAllByProducerName(producerName).stream()
                .map(consumerEntityMapper::mapEntityToDomain)
                .collect(Collectors.toList());
    }

}
