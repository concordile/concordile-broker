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

package io.github.projectleopold.mapper;

/**
 * Mapping from a request (DTO) to a domain and a result domain to a response (DTO).
 *
 * @param <RESULT_DOMAIN> the result domain type
 * @see RequestMapper
 * @see ResponseMapper
 */
public interface MvcMapper<REQUEST, DOMAIN, RESULT_DOMAIN, RESPONSE>
        extends RequestMapper<REQUEST, DOMAIN>, ResponseMapper<RESULT_DOMAIN, RESPONSE> {

    @Override
    DOMAIN mapRequestToDomain(REQUEST request);

    @Override
    RESPONSE mapDomainToResponse(RESULT_DOMAIN domainResult);

}
