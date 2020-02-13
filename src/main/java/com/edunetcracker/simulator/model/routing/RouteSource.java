package com.edunetcracker.simulator.model.routing;

import lombok.Getter;

@Getter
public enum RouteSource {

    LOCAL_PORT('L'),
    CONNECTED_SUBNETWORK('C'),
    STATIC('S'),
    RIP('R'),
    OSPF('O'),
    EIGRP('D');

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    private char oneLetterReduction;

    RouteSource(char oneLetterReduction) {
        this.oneLetterReduction = oneLetterReduction;
    }

    public boolean equals (char other) {
        return oneLetterReduction == other;
    }

    public boolean equals (RouteSource other) {
        if (other == null) {
            return false;
        }
        return oneLetterReduction == other.oneLetterReduction;
    }
}
