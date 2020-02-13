package com.edunetcracker.simulator.service;

import com.edunetcracker.simulator.database.repository.LinkRepository;
import com.edunetcracker.simulator.model.DTO.PingDTO;
import com.edunetcracker.simulator.model.DTO.TrafficDTO;
import com.edunetcracker.simulator.model.context.GeneratedConfig;
import com.edunetcracker.simulator.model.context.NEContext;
import com.edunetcracker.simulator.model.context.TrafficContext;
import com.edunetcracker.simulator.model.element.NetworkElement;
import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.model.element.Switch;
import com.edunetcracker.simulator.service.context.ContextService;
import com.edunetcracker.simulator.service.routingService.IpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.edunetcracker.simulator.model.context.TrafficContext.TrafficType.SIMPLE;

@Service
public class SimulatorService {
    private static Logger logger = LoggerFactory.getLogger(SimulatorService.class);

    private final LinkRepository linkRepository;
    private final RouterService routerService;
    private final SwitchService switchService;
    private final ContextService contextService;

    @Autowired
    public SimulatorService(LinkRepository linkRepository, RouterService routerService, SwitchService switchService, ContextService contextService) {
        this.linkRepository = linkRepository;
        this.routerService = routerService;
        this.switchService = switchService;
        this.contextService = contextService;
    }


    public ResponseEntity<List<NetworkElement>> getComps () {
//        чтение из бд сохраненных параметров
// fixme: wrong method, it is necessary to return object with all information about Scene

        List<NetworkElement> comps = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(comps);
    }



    private void startSwitch (Switch switchNE) {
        switchNE.setWorking(true);
        switchNE.start();
    }

    private void startRouter (Router router) {
        router.setWorking(true);
        router.start();
        router.initializeRunningRoutes();
    }

    public ResponseEntity startComponent(Integer id) {
        NetworkElement ne = switchService.getLoaded(id);
        if (null != ne) {
            startSwitch((Switch)ne);
            return ResponseEntity.ok("Started switch successfully.");
        }
        ne = routerService.getLoaded(id);
        if (null != ne) {
            startRouter((Router)ne);
            return ResponseEntity.ok("Started router successfully.");
        }
        logger.error("Tried to start nonexisting NetworkElement with id {}", id);
        return ResponseEntity.badRequest()
                .body(String.format("Tried to start nonexisting NetworkElement with id %d", id));
    }

    public ResponseEntity startAllComponents() {
        List<Router> routers = routerService.getLoadedRouters();
        List<Switch> switches = switchService.getLoadedSwitches();

        for (Router router : routers) {
            startRouter(router);
            logger.info("Started router with id {}", router.getIdNE());
        }
        for (Switch switchNE : switches) {
            startSwitch(switchNE);
            logger.info("Started switch with id {}", switchNE.getIdNE());
        }

        return ResponseEntity.status(HttpStatus.OK).body("All components have been started");
    }


    public ResponseEntity startComponents (List<Integer> ids) {
        for (Integer id : ids) {
            startComponent(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Request components have been started");
    }

    public ResponseEntity stopComponents () {


        return ResponseEntity.status(HttpStatus.OK).body("All components have been stopped");
    }

    public ResponseEntity stopComponents (List<Integer> ids) {

        return ResponseEntity.status(HttpStatus.OK).body("Request components have been stopped");
    }

    /**
     * Creates a ping context for a router.
     * (All the work of finding necessary port is on context's shoulders).
     * @param pingDTO
     * @return
     */
    public ResponseEntity ping (PingDTO pingDTO) {
        Router router = routerService.getLoaded(pingDTO.getRouterId());
        logger.trace("Requested router id: {}. Found: {}", pingDTO.getRouterId(), router);
        NEContext ping = ContextService.ping(null, IpService.intFromString(pingDTO.getDestIp()));
        router.getContexts().add(ping);

        return ResponseEntity.ok("Context initiated successfully!");
    }

    public ResponseEntity traffic (TrafficDTO trafficDTO){

        //TODO: create routers in routerServices (because now they are null)

        Router router1 = routerService.getLoaded(trafficDTO.getRouterId1());
        Router router2 = routerService.getLoaded(trafficDTO.getRouterId2());
        logger.trace("Requested router 1 id: {}. Found: {}", trafficDTO.getRouterId1(), router1);
        logger.trace("Requested router 2 id: {}. Found: {}", trafficDTO.getRouterId2(), router2);

        long id = new Random().nextLong();
        GeneratedConfig gc1 = new GeneratedConfig(id,true, IpService.intFromString(trafficDTO.getDestIp1()), 10,
                SIMPLE);
        GeneratedConfig gc2 = new GeneratedConfig(id,true, IpService.intFromString(trafficDTO.getDestIp2()), 10,
                SIMPLE);
        NEContext traffic1 = ContextService.traffic(gc1);
        NEContext traffic2 = ContextService.traffic(gc2);
        logger.error("Traffic1 has id {} and is alive {}", traffic1.getId(), traffic1.isAlive());
        logger.error("Router1 is null {}", router1 == null);
        router1.getContexts().add(traffic1);
        //router2.getContexts().add(traffic2);

        return ResponseEntity.ok("Traffic context initiated successfully!");
    }

}
