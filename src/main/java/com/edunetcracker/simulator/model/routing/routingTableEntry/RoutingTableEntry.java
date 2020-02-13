package com.edunetcracker.simulator.model.routing.routingTableEntry;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RoutingTableEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    int ip;

    @Column
    int mask;

    @Column
    int nextHop;

    @Column
    char routeSource;
    //ToDO RouteSource routeSource;

    @Column
    long administrativeDistance;

    @Column
    long metric;


    public RoutingTableEntry() {}

    @Override
    public int hashCode() {
        long hash = ip >> (metric % 4);
        hash += mask;
        hash = hash >> (administrativeDistance % 8);
        hash += routeSource;
        hash = hash ^ nextHop;
        return (int)hash;
    }

    /**
     * Note that the equal routes FROM DIFFERENT ROUTERS (!) will
     * be considered EQUAL!!
     * @param other
     * @return equal or not
     */
    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoutingTableEntry)) {
            return false;
        }

        RoutingTableEntry otherEntry = (RoutingTableEntry) other;

        if (routeSource             != otherEntry.routeSource ||
            administrativeDistance  != otherEntry.administrativeDistance ||
            metric                  != otherEntry.metric ||
            ip                      != otherEntry.ip ||
            mask                    != otherEntry.mask ||
            nextHop                 != otherEntry.nextHop
            ) {
            return false;
        }

        return true;
    }
}
