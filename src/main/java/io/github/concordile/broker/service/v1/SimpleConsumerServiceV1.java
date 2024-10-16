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

package io.github.concordile.broker.service.v1;

import io.github.concordile.broker.payload.v1.ConsumerResponseV1;
import io.github.concordile.broker.payload.v1.ProducerResponseV1;
import io.github.concordile.broker.exception.v1.ConsumerNotFoundExceptionV1;
import io.github.concordile.broker.mapper.payload.v1.ConsumerResponseMapperV1;
import io.github.concordile.broker.mapper.payload.v1.ProducerResponseMapperV1;
import io.github.concordile.broker.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleConsumerServiceV1 implements ConsumerServiceV1 {

    private final ConsumerService consumerService;

    private final ConsumerResponseMapperV1 consumerResponseMapper;
    private final ProducerResponseMapperV1 producerResponseMapper;

    @Override
    public List<ConsumerResponseV1> getConsumers() {
        return consumerService.getAllConsumers().stream()
                .map(consumerResponseMapper::mapDomainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConsumerResponseV1 getConsumer(String consumerName) {
        return consumerService.findConsumer(consumerName)
                .map(consumerResponseMapper::mapDomainToResponse)
                .orElseThrow(() -> new ConsumerNotFoundExceptionV1(consumerName));
    }

    @Override
    public List<ProducerResponseV1> getProducers(String consumerName) {
        return consumerService.getAllProducers(consumerName).stream()
                .map(producerResponseMapper::mapDomainToResponse)
                .collect(Collectors.toList());
    }

}
