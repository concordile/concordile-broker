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

package io.github.projectleopold.mapper.simple;

import io.github.projectleopold.configuration.SpringMapStructConfiguration;
import io.github.projectleopold.domain.NewContract;
import io.github.projectleopold.domain.NewProducer;
import io.github.projectleopold.mapper.SimpleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapStructConfiguration.class)
public interface NewContract2NewProducerMapper
        extends SimpleMapper<NewContract, NewProducer> {

    @Override
    @Mapping(target = "name", source = "producer")
    NewProducer map(NewContract newContract);

}
