package io.github.leopold.repository;

import io.github.leopold.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityParticipantRepository extends ParticipantRepository, JpaRepository<ParticipantEntity, String> {
}
