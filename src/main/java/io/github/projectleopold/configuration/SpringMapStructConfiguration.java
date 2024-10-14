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

package io.github.projectleopold.configuration;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.SubclassExhaustiveStrategy;

@MapperConfig(
        // Spring Dependency Injection
        componentModel = MappingConstants.ComponentModel.SPRING,
        // Dependency Injection with constructors
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        // Prefix MapStruct in implementations
        implementationName = "MapStruct<CLASS_NAME>",
        // Require all mappings or will fail builds
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        // Runtime exceptions when no mappers for all subclasses; you must test it
        subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION,
        // For parent's class setters
        builder = @Builder(disableBuilder = true)
)
public class SpringMapStructConfiguration {
}
