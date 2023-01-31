package io.github.leopold.service.v1;

import io.github.leopold.domain.Contract;
import io.github.leopold.domain.NewContract;
import io.github.leopold.dto.v1.ContractDtoV1;
import io.github.leopold.dto.v1.NewContractDtoV1;
import io.github.leopold.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleContractServiceV1 implements ContractServiceV1 {

    private final ContractService contractService;

    @Override
    public ContractDtoV1 addContract(NewContractDtoV1 dto) {
        //TODO: Move mapping
        NewContract domain = NewContract.builder()
                .producer(dto.getProducer())
                .consumer(dto.getConsumer())
                .build();
        Contract result = contractService.createContract(domain);
        return ContractDtoV1.builder()
                .id(result.getId())
                .producer(result.getProducer())
                .consumer(result.getConsumer())
                .build();
    }

}
