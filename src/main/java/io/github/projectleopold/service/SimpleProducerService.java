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

import io.github.projectleopold.repository.ConsumerRepository;
import io.github.projectleopold.repository.ProducerRepository;
import io.github.projectleopold.domain.Consumer;
import io.github.projectleopold.domain.Producer;
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

    @Override
    public Optional<Producer> findProducer(String producerName) {
        //TODO: Move mapping
        return producerRepository.findByName(producerName)
                .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build());
    }

    @Override
    public List<Producer> getAllProducers() {
        //TODO: Move mapping
        return producerRepository.findAll().stream()
                .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Consumer> getAllConsumers(String producerName) {
        //TODO: Move mapping
        return consumerRepository.findAllByProducerName(producerName).stream()
                .map(consumer -> Consumer.builder()
                        .name(consumer.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
