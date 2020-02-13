package com.edunetcracker.simulator.model.context;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import com.edunetcracker.simulator.service.dataUnit.IP.IpBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PingContext implements NEContext {
    private static Logger logger = LoggerFactory.getLogger(PingContext.class);

    private long id;
    private boolean isAlive;

    private Integer sourceIp;
    private Integer destinationIp;
    private int countdown;

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public List<DataUnit> performAction() {
        logger.info("Ping context with id {}: {} iterations left.", id, countdown);
        if (--countdown <= 0) {
            logger.info("Ping context with id {} is dead.", id);
            isAlive = false;
        }
        DataUnit dataUnit = IpBuilder.ping(sourceIp, destinationIp);
        List<DataUnit> retList = new LinkedList<>();
        retList.add(dataUnit);
        return retList;
    }

    @Override
    public boolean performInput(DataUnit pack) {

//        if (numOfSend == maxSendIters) {
//            state = PingState.COMPLETED;
//        } else {
//            state = PingState.SEND;
//            numOfSend++;
//            if (/*receive reply == */true) {
//                numOfGet++;
//                message.append("!");
//            } else {
//                message.append(".");
//            }
//        }

        return true;
    }

    @Override
    public boolean equals (NEContext another) {
        if (null == another) {
            return false;
        }
        return id == another.getId();
    }
}
