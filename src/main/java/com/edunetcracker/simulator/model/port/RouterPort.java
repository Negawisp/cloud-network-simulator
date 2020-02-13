package com.edunetcracker.simulator.model.port;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.service.routingService.IpService;
import com.edunetcracker.simulator.service.status.SequenceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;

@Getter
@Entity
public class RouterPort extends Port {
    @Transient
    private static Logger logger = LoggerFactory.getLogger(RouterPort.class);

    @Transient
    private boolean withIpAddress;

    @Setter
    @Column(name = "ip")
    @JsonProperty
    private int ip;

    @Setter
    @Column(name = "mask")
    @JsonProperty
    private int mask;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "router", referencedColumnName = "idne")
    private Router router;


    @Override
    public SequenceStatus copyRefs(Port another) {
        if (!(another instanceof RouterPort)) {
            SequenceStatus.PARAMETER_TYPE_INCONSISTENCY.logError("RouterPort");
            return SequenceStatus.PARAMETER_TYPE_INCONSISTENCY;
        }
        super.copyRefs(another);
        RouterPort anotherRP = (RouterPort)another;
        router = anotherRP.router;
        return SequenceStatus.OK;
    }

    public void setAddress (int ip, int mask) {
        this.ip = ip;
        this.mask = mask;
        withIpAddress = true;
    }

    public void setAddress (String ip, String mask) {
        int intIp = IpService.intFromString(ip);
        int intMask = IpService.intFromString(mask);
        setAddress(intIp, intMask);
    }

    @Override
    public Router checkForOwner () {
        if (null == router) {
            SequenceStatus.ROUTER_PORT_NO_OWNER.logWarning(getId());
        }
        return router;
    }

    @Override
    protected DataUnit rejectedDueToOverflow(DataUnit dataUnit) {
        //ToDo(Wisp): Implement router behaviour on port overflow
        throw new NotImplementedException();
    }


}
