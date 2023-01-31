package io.github.leopold.repository;

import io.github.leopold.entity.ConsumerEntity;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository {

    Optional<ConsumerEntity> findByName(String consumerName);

    List<ConsumerEntity> findAll();

    List<ConsumerEntity> findAllByProducerName(String producerName);

}
