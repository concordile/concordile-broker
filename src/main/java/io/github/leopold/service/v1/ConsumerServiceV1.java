package io.github.leopold.service.v1;

import io.github.leopold.dto.v1.ConsumerDtoV1;
import io.github.leopold.dto.v1.ProducerDtoV1;

import java.util.List;

public interface ConsumerServiceV1 {

    List<ConsumerDtoV1> getConsumers();

    ConsumerDtoV1 getConsumer(String consumerName);

    List<ProducerDtoV1> getProducers(String consumerName);

}
