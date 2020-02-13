package com.edunetcracker.simulator.service.configurers;

import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.model.port.RouterPort;
import com.edunetcracker.simulator.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class RouterConfigurer {

    Logger logger = LoggerFactory.getLogger(RouterConfigurer.class);

    @Autowired
    NetworkService networkService;

    public void givePortsTo (Router router) {
        assert router != null;

        if (router.getPhysConfigType() == null) {
            logger.warn("Router with id \"{}\" didn't have a physical configuration type.", router.getIdNE());
            return;
        }

        giveLazyPortsTo(router);
    }

    /**
     * Gives 4 ports to router.
     * @param router Router to give ports to.
     */
    private void giveLazyPortsTo (Router router) {
        assert router != null;

        List<RouterPort> ports = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            RouterPort nextPort = new RouterPort();
            networkService.giveMacToPort(nextPort);
            nextPort.setOrder(i + 1);
            nextPort.setRouter(router);

            ports.add(nextPort);
        }

        router.setPorts(ports);
    }
}
