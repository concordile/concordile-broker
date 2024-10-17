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

package io.github.concordile.broker.controller.v1;

import io.github.concordile.broker.payload.v1.ConsumerResponseV1;
import io.github.concordile.broker.payload.v1.ProducerResponseV1;
import io.github.concordile.broker.service.v1.ProducerServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {

    private final ProducerServiceV1 service;

    @GetMapping
    public List<ProducerResponseV1> getProducers() {
        return service.getProducers();
    }

    @GetMapping("/{producerName}")
    public ProducerResponseV1 getProducer(@PathVariable String producerName) {
        return service.getProducer(producerName);
    }

    @GetMapping("/{producerName}/consumers")
    public List<ConsumerResponseV1> getConsumers(@PathVariable String producerName) {
        return service.getConsumers(producerName);
    }

}
