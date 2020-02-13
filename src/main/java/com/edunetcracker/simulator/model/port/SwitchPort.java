package com.edunetcracker.simulator.model.port;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import com.edunetcracker.simulator.model.element.Switch;
import com.edunetcracker.simulator.service.status.SequenceStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "switch_port")
public class SwitchPort extends Port {
    @Transient
    private static Logger logger = LoggerFactory.getLogger(RouterPort.class);

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "switch", referencedColumnName = "idne")
    private Switch switchEl;

    public SequenceStatus copyRefs (Port another) {
        if (!(another instanceof SwitchPort)) {
            SequenceStatus.PARAMETER_TYPE_INCONSISTENCY.logError("SwitchPort");
            return SequenceStatus.PARAMETER_TYPE_INCONSISTENCY;
        }
        super.copyRefs(another);
        SwitchPort anotherSP = (SwitchPort)another;
        switchEl = anotherSP.switchEl;
        return SequenceStatus.OK;
    }

    @Override
    public Switch checkForOwner () {
        if (null == switchEl) {
            SequenceStatus.SWITCH_PORT_NO_OWNER.logWarning(getId());
        }
        return switchEl;
    }

    @Override
    protected DataUnit rejectedDueToOverflow(DataUnit dataUnit) {
        //ToDo(Wisp): Implement switch behaviour on port overflow
        throw new NotImplementedException();
    }
}
