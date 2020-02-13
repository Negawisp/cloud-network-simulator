package com.edunetcracker.simulator.service.dataUnit.IP;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import com.edunetcracker.simulator.model.dataUnit.ip.IP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpBuilder {
    private static Logger logger = LoggerFactory.getLogger(IpBuilder.class);

    public static IP ping (Integer sourceIp, Integer destinationIp) {
        IP newPacket = new IP();
        newPacket.setSourceIp(sourceIp);
        newPacket.setDestinationIp(destinationIp);
        newPacket.setEncapsulatedType(IP.EncapsulatedType.PING);
        newPacket.setType(DataUnit.Type.IP);

        return newPacket;
    }

    /**
     * Creates a new IP packet to signalise the source of the <i>initialPacket</i>
     * that the destination is unreachable.
     *
     * @param initialPacket
     * @param sourceIp Source IP of the new packet.
     * @return Brand new IP packet to return to the sender.
     */
    public static IP destinationUnreachable (IP initialPacket, Integer sourceIp) {
        //TODO (Wisp): Implement destinationUnreachable (...)
        logger.warn("Method destinationUnreachable is written wham-bam. Remake it one day plz!");
        IP newPacket = new IP();
        newPacket.setSourceIp(sourceIp);
        newPacket.setDestinationIp(initialPacket.getSourceIp());
        newPacket.encapsulate(initialPacket);
        return newPacket;
    }
}
