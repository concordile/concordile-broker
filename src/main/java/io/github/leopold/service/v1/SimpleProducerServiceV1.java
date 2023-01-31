package io.github.leopold.service.v1;

import io.github.leopold.dto.v1.ConsumerDtoV1;
import io.github.leopold.dto.v1.ProducerDtoV1;
import io.github.leopold.exception.v1.ProducerNotFoundExceptionV1;
import io.github.leopold.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleProducerServiceV1 implements ProducerServiceV1 {

    private final ProducerService producerService;

    @Override
    public List<ProducerDtoV1> getProducers() {
        //TODO: Move mapping
        return producerService.getAllProducers().stream()
                .map(producer -> ProducerDtoV1.builder()
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProducerDtoV1 getProducer(String producerName) {
        //TODO: Move mapping
        return producerService.findProducer(producerName)
                .map(producer -> ProducerDtoV1.builder()
                        .name(producer.getName())
                        .build())
                .orElseThrow(() -> new ProducerNotFoundExceptionV1(producerName));
    }

    @Override
    public List<ConsumerDtoV1> getConsumers(String producerName) {
        //TODO: Move mapping
        return producerService.getAllConsumers(producerName).stream()
                .map(consumer -> ConsumerDtoV1.builder()
                        .name(consumer.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
