package io.github.leopold.service.v1;

import io.github.leopold.dto.v1.ConsumerDtoV1;
import io.github.leopold.dto.v1.ProducerDtoV1;
import io.github.leopold.exception.v1.ConsumerNotFoundExceptionV1;
import io.github.leopold.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleConsumerServiceV1 implements ConsumerServiceV1 {

    private final ConsumerService consumerService;

    @Override
    public List<ConsumerDtoV1> getConsumers() {
        //TODO: Move mapping
        return consumerService.getAllConsumers().stream()
                .map(consumer -> ConsumerDtoV1.builder()
                        .name(consumer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ConsumerDtoV1 getConsumer(String consumerName) {
        //TODO: Move mapping
        return consumerService.findConsumer(consumerName)
                .map(consumer -> ConsumerDtoV1.builder()
                        .name(consumer.getName())
                        .build())
                .orElseThrow(() -> new ConsumerNotFoundExceptionV1(consumerName));
    }

    @Override
    public List<ProducerDtoV1> getProducers(String consumerName) {
        //TODO: Move mapping
        return consumerService.getAllProducers(consumerName).stream()
                .map(producer -> ProducerDtoV1.builder()
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
