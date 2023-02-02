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

package io.github.leopold.service;

import io.github.leopold.repository.ConsumerRepository;
import io.github.leopold.repository.ProducerRepository;
import io.github.leopold.domain.Consumer;
import io.github.leopold.domain.Producer;
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

    @Override
    public Optional<Consumer> findConsumer(String consumerName) {
        //TODO: Move mapping
        return consumerRepository.findByName(consumerName)
                .map(consumer -> Consumer.builder()
                        .name(consumer.getName())
                        .build());
    }

    @Override
    public List<Consumer> getAllConsumers() {
        //TODO: Move mapping
        return consumerRepository.findAll().stream()
                .map(consumer -> Consumer.builder()
                        .name(consumer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Producer> getAllProducers(String consumerName) {
        //TODO: Move mapping
        return producerRepository.findAllByConsumerName(consumerName).stream()
                .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
