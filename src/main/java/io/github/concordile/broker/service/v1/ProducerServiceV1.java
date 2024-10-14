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

import java.util.List;

public interface ProducerServiceV1 {

    List<ProducerDtoV1> getProducers();

    ProducerDtoV1 getProducer(String producerName);

    List<ConsumerDtoV1> getConsumers(String producerName);

}
