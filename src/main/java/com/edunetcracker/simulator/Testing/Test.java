package com.edunetcracker.simulator.Testing;

import com.edunetcracker.simulator.model.DTO.TrafficDTO;
import com.edunetcracker.simulator.model.Scene;
import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.service.RouterService;
import com.edunetcracker.simulator.service.SceneService;
import com.edunetcracker.simulator.service.SimulatorService;
import com.edunetcracker.simulator.service.configurers.RouterConfigurer;
import com.edunetcracker.simulator.service.routingService.IpService;
import com.edunetcracker.simulator.service.status.SequenceStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Test {
    private static SceneService     sceneService;
    private static RouterService    routerService;
    private static RouterConfigurer routerConfigurer;
    private static SimulatorService simulatorService;

    @Autowired
    public Test(SceneService sceneService, RouterService routerService, RouterConfigurer routerConfigurer, SimulatorService simulatorService) {
        this.sceneService = sceneService;
        this.routerService = routerService;
        this.routerConfigurer = routerConfigurer;
        this.simulatorService = simulatorService;
    }


    public static void doTest() {
        Scene scene = new Scene();
        scene.setId(0);
        scene.setName("TestingScene");

        Router router1 = new Router();
        router1.setPhysConfigType("any");
        log.error("Router1 is null {}", routerService == null);
        routerService.create(router1);

        Router router2 = new Router();
        router2.setPhysConfigType("any");
        log.error("Router2 is null {}", routerService == null);
        routerService.create(router2);
        router2.getPort(0).setIp(192168);

        TrafficDTO trafficDTO = new TrafficDTO();
        trafficDTO.setRouterId1(router1.getId());
        trafficDTO.setRouterId2(router2.getId());
        log.info("Router2 port0 ip is {}", router2.getPort(0).getIp());
        trafficDTO.setDestIp1(IpService.stringFromInt(router1.getPort(0).getIp()));
        trafficDTO.setDestIp2(IpService.stringFromInt(router2.getPort(0).getIp()));

        simulatorService.traffic(trafficDTO);
    }
}