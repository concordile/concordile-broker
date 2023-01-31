package io.github.leopold.controller.v1;

import io.github.leopold.dto.v1.ContractDtoV1;
import io.github.leopold.dto.v1.NewContractDtoV1;
import io.github.leopold.service.v1.ContractServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ContractControllerV1 {

    private final ContractServiceV1 service;

    @PostMapping
    public ContractDtoV1 addContract(@RequestBody NewContractDtoV1 contract) {
        return service.addContract(contract);
    }

}
