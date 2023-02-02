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

package io.github.leopold.repository;

import io.github.leopold.entity.ParticipantEntity;
import io.github.leopold.entity.ProducerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EntityProducerRepository implements ProducerRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<ProducerEntity> findByName(String producerName) {
        TypedQuery<ParticipantEntity> query = entityManager.createQuery("""
                SELECT p
                FROM Participant p
                WHERE p.name = :name
                  AND EXISTS (SELECT c
                              FROM Contract c
                              WHERE c.producer = p.name)
                """, ParticipantEntity.class);
        query.setParameter("name", producerName);
        try {
            //TODO: Move mapping
            ParticipantEntity participant = query.getSingleResult();
            return Optional.of(ProducerEntity.builder()
                    .name(participant.getName())
                    .build());
        } catch (NoResultException ignore) {
            return Optional.empty();
        }
    }

    @Override
    public List<ProducerEntity> findAll() {
        TypedQuery<ParticipantEntity> query = entityManager.createQuery("""
                SELECT p
                FROM Participant p
                WHERE p.name IN (SELECT DISTINCT producer
                                 FROM Contract)
                """, ParticipantEntity.class);
        //TODO: Move mapping
        List<ParticipantEntity> participants = query.getResultList();
        return participants.stream()
                .map(participant -> ProducerEntity.builder()
                        .name(participant.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProducerEntity> findAllByConsumerName(String consumerName) {
        TypedQuery<ParticipantEntity> query = entityManager.createQuery("""
                SELECT p
                FROM Participant p
                WHERE p.name IN (SELECT DISTINCT producer
                                 FROM Contract
                                 WHERE consumer = :name)
                """, ParticipantEntity.class);
        query.setParameter("name", consumerName);
        //TODO: Move mapping
        List<ParticipantEntity> participants = query.getResultList();
        return participants.stream()
                .map(participant -> ProducerEntity.builder()
                        .name(participant.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
