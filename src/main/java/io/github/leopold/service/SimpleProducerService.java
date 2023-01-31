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
public class SimpleProducerService implements ProducerService {

    private final ProducerRepository producerRepository;
    private final ConsumerRepository consumerRepository;

    @Override
    public Optional<Producer> findProducer(String producerName) {
        //TODO: Move mapping
        return producerRepository.findByName(producerName)
                .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build());
    }

    @Override
    public List<Producer> getAllProducers() {
        //TODO: Move mapping
        return producerRepository.findAll().stream()
                .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Consumer> getAllConsumers(String producerName) {
        //TODO: Move mapping
        return consumerRepository.findAllByProducerName(producerName).stream()
                .map(consumer -> Consumer.builder()
                        .name(consumer.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
