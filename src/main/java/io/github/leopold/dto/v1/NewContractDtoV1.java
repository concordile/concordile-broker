package io.github.leopold.dto.v1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewContractDtoV1 {

    private String producer;
    private String consumer;

}
