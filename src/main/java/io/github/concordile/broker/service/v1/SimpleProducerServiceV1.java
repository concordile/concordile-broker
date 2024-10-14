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

import io.github.concordile.broker.dto.v1.ConsumerDtoV1;
import io.github.concordile.broker.dto.v1.ProducerDtoV1;
import io.github.concordile.broker.exception.v1.ProducerNotFoundExceptionV1;
import io.github.concordile.broker.mapper.dto.v1.ConsumerResponseMapperV1;
import io.github.concordile.broker.mapper.dto.v1.ProducerResponseMapperV1;
import io.github.concordile.broker.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleProducerServiceV1 implements ProducerServiceV1 {

    private final ProducerService producerService;

    private final ProducerResponseMapperV1 producerResponseMapper;
    private final ConsumerResponseMapperV1 consumerResponseMapper;

    @Override
    public List<ProducerDtoV1> getProducers() {
        return producerService.getAllProducers().stream()
                .map(producerResponseMapper::mapDomainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProducerDtoV1 getProducer(String producerName) {
        return producerService.findProducer(producerName)
                .map(producerResponseMapper::mapDomainToResponse)
                .orElseThrow(() -> new ProducerNotFoundExceptionV1(producerName));
    }

    @Override
    public List<ConsumerDtoV1> getConsumers(String producerName) {
        return producerService.getAllConsumers(producerName).stream()
                .map(consumerResponseMapper::mapDomainToResponse)
                .collect(Collectors.toList());
    }

}
