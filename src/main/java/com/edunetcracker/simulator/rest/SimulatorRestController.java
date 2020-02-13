package com.edunetcracker.simulator.rest;

import com.edunetcracker.simulator.Testing.Test;
import com.edunetcracker.simulator.model.DTO.PingDTO;
import com.edunetcracker.simulator.model.DTO.TrafficDTO;
import com.edunetcracker.simulator.model.Link;
import com.edunetcracker.simulator.model.element.NetworkElement;
import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.service.LinkService;
import com.edunetcracker.simulator.service.RouterService;
import com.edunetcracker.simulator.service.SimulatorService;
import com.edunetcracker.simulator.service.status.SequenceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/simulation")
public class SimulatorRestController extends RestControllerImpl {
    private static Logger logger = LoggerFactory.getLogger(SimulatorRestController.class);

    private final RouterService routerService;
    private final LinkService linkService;
    private final SimulatorService simulatorService;

    @Autowired
    public SimulatorRestController(RouterService routerService, SimulatorService simulatorService, LinkService linkService) {
        this.routerService = routerService;
        this.simulatorService = simulatorService;
        this.linkService = linkService;
    }


    /**
     * Links 2 components
     * input LinkDTO with id of 2 components and id of linking interfaces
     * returns OK*/
    @RequestMapping(value = "/link", method = RequestMethod.POST)
    public ResponseEntity linkComponents (@RequestBody Link link){
        SequenceStatus ss = linkService.linkComps(link);
        logger.info("ConnA ok: {}", link.getConnA().getPort().getConnection() == link.getConnA());
        logger.info("ConnZ ok: {}", link.getConnZ().getPort().getConnection() == link.getConnZ());
        return ResponseEntity.ok(toJson(link));
    }


    @RequestMapping(value = "/link", method = RequestMethod.DELETE)
    public ResponseEntity deleteLink (@RequestBody Link link) {
        return ResponseEntity.status(linkService.delete(link).getHttpStatus()).build();
    }




    /*Returns saved state of network
    * later we can add args to load one of many saved states*/
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<NetworkElement>> getComponents (){
        return simulatorService.getComps();
    }

    /*Starts all components
    * returns OK*/
    @RequestMapping(value = "/startAll", method = RequestMethod.POST)
    public ResponseEntity startAllComponents () {
        return simulatorService.startAllComponents();
    }

    /**
     * Starts only stated components
     * input int[] id with id of those components which you want to start
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity startComponent (@RequestBody Integer id) {
        return simulatorService.startComponent(id);
    }

    /*Stops all components
     * returns OK*/
    @RequestMapping(value = "/stopAll", method = RequestMethod.POST)
    public ResponseEntity stopComponents(){
        return simulatorService.stopComponents();
    }

    /*Stops only stated components
     * input int[] id with id of those components which you want to stop
     * returns OK*/
    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public ResponseEntity stopComponents(@RequestBody List<Integer> ids){
        return simulatorService.stopComponents(ids);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public ResponseEntity ping (@RequestBody PingDTO pingDTO) {
        return simulatorService.ping(pingDTO);
    }

    //todo(dooly): write rest
    @RequestMapping(value = "/traffic", method = RequestMethod.POST)
    public ResponseEntity traffic (@RequestBody TrafficDTO trafficDTO) {
        return simulatorService.traffic(trafficDTO);
    }

    @RequestMapping(value = "/test_rest", method = RequestMethod.POST)
    public ResponseEntity testRest () {
        Test.doTest();
        return ResponseEntity.ok("Test section is ok");
    }



    @RequestMapping(value = "/fast_launch", method = RequestMethod.POST)
    public ResponseEntity fastLaunch () {
        Router router1 = new Router();
        router1.setPhysConfigType("any");
        routerService.create(router1);

        Router router2 = new Router();
        router2.setPhysConfigType("any");
        routerService.create(router2);

        Link link = new Link();
        link.setPortAid(router1.getPort(1).getId());
        link.setPortZid(router2.getPort(1).getId());
        linkService.linkComps(link);

        simulatorService.startAllComponents();

        routerService.setAddress(router1.getIdNE(), 1, "192.168.0.1", "255.255.255.0");
        routerService.setAddress(router2.getIdNE(), 1, "192.168.0.2", "255.255.255.0");

        return ResponseEntity.ok(String.format("Launched routers with ids %d and %d successfully.",
                                                router1.getIdNE(), router2.getIdNE()));
    }
}
