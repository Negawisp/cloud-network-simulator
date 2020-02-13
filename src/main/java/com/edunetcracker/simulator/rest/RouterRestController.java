package com.edunetcracker.simulator.rest;

import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.model.port.RouterPort;
import com.edunetcracker.simulator.service.RouterService;
import com.edunetcracker.simulator.service.configurers.RouterConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/router")
public class RouterRestController extends RestControllerImpl {
    private static Logger logger = LoggerFactory.getLogger(RouterRestController.class);

    @Autowired
    private RouterService routerService;
    @Autowired
    private RouterConfigurer routerConfigurer;

    //todo: test generator
    //2 routers - ports -ips and link the ports
    //call sim service, which will add context
    //autowired simulator service
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public boolean test () {
        Router router = new Router();
        RouterPort port = new RouterPort();
        router.getPorts().add(port);

        Router router1 = routerService.update(router);
        long id = router.getIdNE();

        logger.info("'router' has {} ports", router.getPorts().size());
        logger.info("'router1' has {} ports", router1.getPorts().size());
        logger.info("router's port's router is 'router': {}", router.getPorts().get(0).getRouter() == router);
        router.getPorts().get(0).checkForOwner();
        logger.info("router1's port's router is 'router': {}", router1.getPorts().get(0).getRouter() == router1);
        router1.getPorts().get(0).checkForOwner();
        logger.info("router1's port is router's port: {}", router1.getPorts().get(0) == router.getPorts().get(0));
        logger.info("<router1's port's router> is <router's port's router>: {}", router1.getPorts().get(0).getRouter() == router.getPorts().get(0).getRouter());



        return router1 == router;
    }

    /**
     * @param router DTO of a Router, containing <b>x, y,</b> and <b>physConfigType</b> fields.
     *               Example:
     *               {
     * 	                 "idNE":"0",
     * 	                 "physConfigType":"any",
     * 	                 "x":"2",
     * 	                 "y":"2"
     *               }
     * @return Response entity with complete Router, or response entity with a correlating error.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addNewRouter (@RequestBody Router router){
        routerService.create(router);
        logger.info("Router created!");
        return ResponseEntity.ok(toJson(router));
    }

    /*Changes router parameters
     * input RouterDTO with current id and new parameters
     * returns JSON of an updated router*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateRouter (@RequestBody Router router){
        routerService.update(router);
        return ResponseEntity.ok(toJson(router));
    }

    @RequestMapping(value = "/setIp", method = RequestMethod.POST)
    public ResponseEntity setIp (@RequestParam Long routerId,
                                 @RequestParam Integer portNumber,
                                 @RequestParam String ip,
                                 @RequestParam String mask) {
        return routerService.setAddress(routerId, portNumber, ip, mask);
    }
}
