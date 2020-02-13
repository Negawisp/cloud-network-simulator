package com.edunetcracker.simulator.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class PingDTO {
    @JsonProperty
    Long routerId;
    @JsonProperty
    String destIp;
}
