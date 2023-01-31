package io.github.leopold.controller.v1;

import io.github.leopold.dto.v1.ConsumerDtoV1;
import io.github.leopold.dto.v1.ProducerDtoV1;
import io.github.leopold.service.v1.ProducerServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {

    private final ProducerServiceV1 service;

    @GetMapping
    public List<ProducerDtoV1> getProducers() {
        return service.getProducers();
    }

    @GetMapping("/{producerName}")
    public ProducerDtoV1 getProducer(@PathVariable String producerName) {
        return service.getProducer(producerName);
    }

    @GetMapping("/{producerName}/consumers")
    public List<ConsumerDtoV1> getConsumers(@PathVariable String producerName) {
        return service.getConsumers(producerName);
    }

}
