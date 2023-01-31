package io.github.leopold.service;

import io.github.leopold.domain.Consumer;
import io.github.leopold.domain.Producer;

import java.util.List;
import java.util.Optional;

public interface ConsumerService {

    Optional<Consumer> findConsumer(String consumerName);

    List<Consumer> getAllConsumers();

    List<Producer> getAllProducers(String consumerName);

}
