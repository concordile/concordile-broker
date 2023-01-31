package io.github.leopold.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract")
@Entity(name = "Contract")
public class ContractEntity {

    @Id
    private String id;

    @Nonnull
    private String producer;

    @Nonnull
    private String consumer;

}
