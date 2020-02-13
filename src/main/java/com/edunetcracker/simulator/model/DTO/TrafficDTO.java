package com.edunetcracker.simulator.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficDTO {
    @JsonProperty
    Long routerId1;
    @JsonProperty
    Long routerId2;
    @JsonProperty
    String destIp1;
    @JsonProperty
    String destIp2;
}
