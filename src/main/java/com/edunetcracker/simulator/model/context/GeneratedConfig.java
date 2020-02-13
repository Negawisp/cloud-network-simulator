package com.edunetcracker.simulator.model.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneratedConfig {
    private long id;
    private boolean isAlive;
    private Integer destinationIP;
    //time between packets are sent
    private long timeBetPack;
    private TrafficContext.TrafficType trafficType;
}
