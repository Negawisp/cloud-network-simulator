package com.edunetcracker.simulator.model.routing.routingTableEntry;

import com.edunetcracker.simulator.model.routing.RouteSource;
import com.edunetcracker.simulator.service.routingService.IpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoutingTableEntryFactory {
    private static Logger logger = LoggerFactory.getLogger(RoutingTableEntry.class);

    public static RoutingTableEntry createLocalPortEntry (int portIP) {
        RoutingTableEntry localPortEntry = new RoutingTableEntry();

        localPortEntry.setIp(portIP);
        localPortEntry.setMask(0xFFFFFFFF);
        localPortEntry.setAdministrativeDistance(0);
        localPortEntry.setMetric(0);
        localPortEntry.setNextHop(0);
        localPortEntry.setRouteSource(RouteSource.LOCAL_PORT.getOneLetterReduction());

        logger.info("Local port entry created successfully.");
        return localPortEntry;
    }

    public static RoutingTableEntry createConnectedSubnetEntry (int portIP, int mask) {
        RoutingTableEntry connectedSubnetEntry = new RoutingTableEntry();
        int subnetIP = portIP & mask;

        connectedSubnetEntry.setIp(subnetIP);
        connectedSubnetEntry.setMask(mask);
        connectedSubnetEntry.setAdministrativeDistance(0);
        connectedSubnetEntry.setMetric(0);
        connectedSubnetEntry.setNextHop(0);
        connectedSubnetEntry.setRouteSource(RouteSource.CONNECTED_SUBNETWORK.getOneLetterReduction());

        logger.info("Connected subnet entry created successfully.");
        return connectedSubnetEntry;
    }

    public static RoutingTableEntry createStaticEntry (int destinationIP, int destinationMask, int nextHop) {
        if (!IpService.isRouteLegal(destinationIP, destinationMask)) {
            return null;
        }

        RoutingTableEntry staticEntry = new RoutingTableEntry();

        staticEntry.setIp(destinationIP);
        staticEntry.setMask(destinationMask);
        staticEntry.setAdministrativeDistance(1);
        staticEntry.setMetric(0);
        staticEntry.setNextHop(nextHop);
        staticEntry.setRouteSource(RouteSource.STATIC.getOneLetterReduction());

        return staticEntry;
    }

    // TODO (VLAD): add more methods for working with mask and ip
}
