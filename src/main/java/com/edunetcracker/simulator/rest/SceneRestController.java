package com.edunetcracker.simulator.rest;

import com.edunetcracker.simulator.model.Scene;
import com.edunetcracker.simulator.service.SceneService;
import com.edunetcracker.simulator.service.status.SequenceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scene")
public class SceneRestController extends RestControllerImpl {

    private final SceneService sceneService;

    @Autowired
    public SceneRestController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity newScene () {
        Scene newScene = new Scene();
        SequenceStatus ss = sceneService.create(newScene);
        return ResponseEntity.ok(toJson(newScene));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getSceneById (@RequestBody int id){
        Scene scene = sceneService.get(id);
        return ResponseEntity.ok(toJson(scene));
    }
}
