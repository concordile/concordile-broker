package io.github.leopold.service;

import io.github.leopold.repository.ConsumerRepository;
import io.github.leopold.repository.ProducerRepository;
import io.github.leopold.domain.Consumer;
import io.github.leopold.domain.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleConsumerService implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final ProducerRepository producerRepository;

    @Override
    public Optional<Consumer> findConsumer(String consumerName) {
        //TODO: Move mapping
        return consumerRepository.findByName(consumerName)
                .map(consumer -> Consumer.builder()
                        .name(consumer.getName())
                        .build());
    }

    @Override
    public List<Consumer> getAllConsumers() {
        //TODO: Move mapping
        return consumerRepository.findAll().stream()
                .map(consumer -> Consumer.builder()
                        .name(consumer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Producer> getAllProducers(String consumerName) {
        //TODO: Move mapping
        return producerRepository.findAllByConsumerName(consumerName).stream()
                .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
