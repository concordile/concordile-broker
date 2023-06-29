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

package io.github.projectleopold.repository;

import io.github.projectleopold.entity.ConsumerEntity;
import io.github.projectleopold.entity.ParticipantEntity;
import io.github.projectleopold.mapper.entity.Consumer2ParticipantEntityBackMapper;
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
public class EntityConsumerRepository implements ConsumerRepository {

    private final EntityManager entityManager;

    private final ParticipantRepository participantRepository;

    private final Consumer2ParticipantEntityBackMapper entityMapper;

    @Override
    public ConsumerEntity save(ConsumerEntity consumer) {
        ParticipantEntity participant = entityMapper.map(consumer);
        ParticipantEntity result = participantRepository.save(participant);
        return entityMapper.mapBack(result);
    }

    @Override
    public Optional<ConsumerEntity> findByName(String consumerName) {
        TypedQuery<ParticipantEntity> query = entityManager.createQuery("""
                SELECT p
                FROM Participant p
                WHERE p.name = :name
                  AND EXISTS (SELECT c
                              FROM Contract c
                              WHERE c.consumer = p.name)
                """, ParticipantEntity.class);
        query.setParameter("name", consumerName);
        try {
            ParticipantEntity result = query.getSingleResult();
            return Optional.of(entityMapper.mapBack(result));
        } catch (NoResultException ignore) {
            return Optional.empty();
        }
    }

    @Override
    public List<ConsumerEntity> findAll() {
        TypedQuery<ParticipantEntity> query = entityManager.createQuery("""
                SELECT p
                FROM Participant p
                WHERE p.name IN (SELECT DISTINCT consumer
                                 FROM Contract)
                """, ParticipantEntity.class);
        List<ParticipantEntity> results = query.getResultList();
        return results.stream()
                .map(entityMapper::mapBack)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsumerEntity> findAllByProducerName(String producerName) {
        TypedQuery<ParticipantEntity> query = entityManager.createQuery("""
                SELECT p
                FROM Participant p
                WHERE p.name IN (SELECT DISTINCT consumer
                                 FROM Contract
                                 WHERE producer = :name)
                """, ParticipantEntity.class);
        query.setParameter("name", producerName);
        List<ParticipantEntity> results = query.getResultList();
        return results.stream()
                .map(entityMapper::mapBack)
                .collect(Collectors.toList());
    }

}
