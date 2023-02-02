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

import io.github.leopold.domain.Participant;
import io.github.leopold.entity.ParticipantEntity;
import io.github.leopold.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleParticipantService implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    public Participant createParticipant(String name) {
        //TODO: Move mapping
        ParticipantEntity entity = ParticipantEntity.builder()
                .name(name)
                .build();
        ParticipantEntity result = participantRepository.save(entity);
        return Participant.builder()
                .name(result.getName())
                .build();
    }

}
