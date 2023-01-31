package io.github.leopold.service.v1;

import io.github.leopold.dto.v1.ConsumerDtoV1;
import io.github.leopold.dto.v1.ProducerDtoV1;

import java.util.List;

public interface ProducerServiceV1 {

    List<ProducerDtoV1> getProducers();

    ProducerDtoV1 getProducer(String producerName);

    List<ConsumerDtoV1> getConsumers(String producerName);

}
