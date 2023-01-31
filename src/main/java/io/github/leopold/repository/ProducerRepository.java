package io.github.leopold.repository;

import io.github.leopold.entity.ProducerEntity;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository {

    Optional<ProducerEntity> findByName(String producerName);

    List<ProducerEntity> findAll();

    List<ProducerEntity> findAllByConsumerName(String consumerName);

}
