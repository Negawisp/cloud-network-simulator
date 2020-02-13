package com.edunetcracker.simulator.rest;

import com.edunetcracker.simulator.model.DBObject;

public interface RestController {

    /**
     * Creates a JSON out of a DBObject
     * @param obj instance of DBObject with @JsonAnnotated fields
     * @return Json
     */
    String toJson (DBObject obj);
}
