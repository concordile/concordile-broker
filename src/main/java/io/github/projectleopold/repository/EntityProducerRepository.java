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

import io.github.projectleopold.entity.ParticipantEntity;
import io.github.projectleopold.entity.ProducerEntity;
import io.github.projectleopold.mapper.entity.Producer2ParticipantEntityBackMapper;
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

    private final ParticipantRepository participantRepository;

    private final Producer2ParticipantEntityBackMapper entityMapper;

    @Override
    public ProducerEntity save(ProducerEntity producer) {
        ParticipantEntity participant = entityMapper.map(producer);
        ParticipantEntity result = participantRepository.save(participant);
        return entityMapper.mapBack(result);
    }

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
            ParticipantEntity result = query.getSingleResult();
            return Optional.of(entityMapper.mapBack(result));
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
        List<ParticipantEntity> results = query.getResultList();
        return results.stream()
                .map(entityMapper::mapBack)
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
        List<ParticipantEntity> results = query.getResultList();
        return results.stream()
                .map(entityMapper::mapBack)
                .collect(Collectors.toList());
    }

}
