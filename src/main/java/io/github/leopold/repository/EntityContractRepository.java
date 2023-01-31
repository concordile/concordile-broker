package io.github.leopold.repository;

import io.github.leopold.entity.ContractEntity;
import org.springframework.data.repository.CrudRepository;

public interface EntityContractRepository extends ContractRepository, CrudRepository<ContractEntity, Long> {
}
