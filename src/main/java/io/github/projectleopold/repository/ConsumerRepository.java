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

package io.github.projectleopold.repository;

import io.github.projectleopold.entity.ConsumerEntity;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository {

    ConsumerEntity save(ConsumerEntity consumer);

    Optional<ConsumerEntity> findByName(String consumerName);

    List<ConsumerEntity> findAll();

    List<ConsumerEntity> findAllByProducerName(String producerName);
}
