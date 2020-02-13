package com.edunetcracker.simulator.service.context;

import com.edunetcracker.simulator.model.context.GeneratedConfig;
import com.edunetcracker.simulator.model.context.NEContext;
import com.edunetcracker.simulator.model.context.PingContext;
import com.edunetcracker.simulator.model.context.TrafficContext;
import com.edunetcracker.simulator.model.dataUnit.ip.IP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class ContextService {
    private static Logger logger = LoggerFactory.getLogger(ContextService.class);

    /**
     * Makes context out of incoming ipPacket, destination of which
     * is no the router calling this method.
     * @param ipPacket
     * @return
     */
    public static NEContext fromTransitionalIp(IP ipPacket) {
        //TODO(Wisp): implement fromTransitionalIP (IP ipPacket)
        logger.warn("ContextService.fromTransitionalIp(...) is not implemented!!");
        return null;
    }

    public static NEContext fromAcceptedIp (IP ipPacket) {
        //TODO(Wisp): implement fromAcceptedIp (IP ipPacket)
        logger.warn("ContextService.fromAcceptedIp(...) is not implemented!!");
        return null;
    }

    public static NEContext ping (Integer sourceIp, Integer destinationIp) {
        PingContext context = new PingContext();
        context.setSourceIp(sourceIp);
        context.setDestinationIp(destinationIp);
        context.setCountdown(5);
        context.setAlive(true);
        return context;
    }

    //todo(dasha):create similar func to traffic
    public static NEContext traffic(GeneratedConfig config){
        return new TrafficContext(config);
    }

}
