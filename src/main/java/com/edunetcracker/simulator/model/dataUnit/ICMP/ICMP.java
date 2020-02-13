package com.edunetcracker.simulator.model.dataUnit.ICMP;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import com.edunetcracker.simulator.model.dataUnit.ip.IP;
import lombok.Getter;
import lombok.Setter;

public class ICMP extends DataUnit {

    //@Getter
    //@Setter
    private TypeICMP type;

    @Getter
    @Setter
    private IP ipHeader;

    public ICMP(){
        setType(Type.ICMP);
    }

    public enum TypeICMP{
        ECHO_REPLY,
        DEST_UNREACH,
        ECHO_REQ,
        TIME_EXCEEDED
    }
}
