/*
 * Copyright 2023-2024 The Concordile Authors
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

package io.github.projectleopold.controller.v1;

import io.github.projectleopold.dto.v1.ContractDtoV1;
import io.github.projectleopold.dto.v1.NewContractDtoV1;
import io.github.projectleopold.service.v1.ContractServiceV1;
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
