/*
 * Copyright 2023 The Project Leopold Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.projectleopold.service.v1;

import io.github.projectleopold.domain.Contract;
import io.github.projectleopold.domain.NewContract;
import io.github.projectleopold.dto.v1.ContractDtoV1;
import io.github.projectleopold.dto.v1.NewContractDtoV1;
import io.github.projectleopold.mapper.dto.v1.ContractResponseMapperV1;
import io.github.projectleopold.mapper.dto.v1.NewContractRequestMapperV1;
import io.github.projectleopold.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleContractServiceV1 implements ContractServiceV1 {

    private final ContractService contractService;

    private final NewContractRequestMapperV1 newContractRequestMapper;
    private final ContractResponseMapperV1 contractResponseMapper;

    @Override
    public ContractDtoV1 addContract(NewContractDtoV1 dto) {
        NewContract domain = newContractRequestMapper.mapRequestToDomain(dto);
        Contract result = contractService.createContract(domain);
        return contractResponseMapper.mapDomainToResponse(result);
    }

}
