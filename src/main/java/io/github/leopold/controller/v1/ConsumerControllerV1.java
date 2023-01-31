package io.github.leopold.controller.v1;

import io.github.leopold.dto.v1.ConsumerDtoV1;
import io.github.leopold.dto.v1.ProducerDtoV1;
import io.github.leopold.service.v1.ConsumerServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/consumers")
public class ConsumerControllerV1 {

    private final ConsumerServiceV1 service;

    @GetMapping
    public List<ConsumerDtoV1> getConsumers() {
        return service.getConsumers();
    }

    @GetMapping("/{consumerName}")
    public ConsumerDtoV1 getConsumer(@PathVariable String consumerName) {
        return service.getConsumer(consumerName);
    }

    @GetMapping("/{consumerName}/producers")
    public List<ProducerDtoV1> getProducers(@PathVariable String consumerName) {
        return service.getProducers(consumerName);
    }

}
