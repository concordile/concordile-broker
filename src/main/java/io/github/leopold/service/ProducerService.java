package io.github.leopold.service;

import io.github.leopold.domain.Consumer;
import io.github.leopold.domain.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerService {

    Optional<Producer> findProducer(String producerName);

    List<Producer> getAllProducers();

    List<Consumer> getAllConsumers(String producerName);

}
