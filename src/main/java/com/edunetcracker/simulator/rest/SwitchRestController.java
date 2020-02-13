package com.edunetcracker.simulator.rest;


import com.edunetcracker.simulator.model.element.Switch;
import com.edunetcracker.simulator.service.SwitchService;
import com.edunetcracker.simulator.service.status.SequenceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/switch")
public class SwitchRestController extends RestControllerImpl {

    @Autowired
    private final SwitchService switchService;

    @Autowired
    public SwitchRestController(SwitchService switchService) {
        this.switchService = switchService;
    }

    /*Add new switch
     * input SwitchDTO (without id )
     * returns id of added switch*/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addNewSwitch (@RequestBody Switch switchEl){
        SequenceStatus ss = switchService.create(switchEl);
        return ResponseEntity.ok(toJson(switchEl));
    }

    /*Changes switch parameters
     * input SwitchDTO with current id and new parameters
     * returns OK*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity updateRouter (@RequestBody Switch switchEl){
//ToDo: Generate & return correct ResponseEntity
        switchEl = switchService.update(switchEl);
        return ResponseEntity.ok(toJson(switchEl));
    }
}
