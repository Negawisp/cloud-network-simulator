package com.edunetcracker.simulator.model.dataUnit.ip;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import lombok.Getter;
import lombok.Setter;

public class IP extends DataUnit {
    @Setter
    @Getter
    private Integer sourceIp;

    @Setter
    @Getter
    private Integer destinationIp;

    public enum EncapsulatedType {
        ICMP,
        PING,
        TCP,
        UDP
    }

    @Setter
    @Getter
    private EncapsulatedType encapsulatedType;
}
