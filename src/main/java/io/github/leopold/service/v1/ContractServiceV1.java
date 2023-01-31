package io.github.leopold.service.v1;

import io.github.leopold.dto.v1.ContractDtoV1;
import io.github.leopold.dto.v1.NewContractDtoV1;

public interface ContractServiceV1 {

    ContractDtoV1 addContract(NewContractDtoV1 contract);

}
