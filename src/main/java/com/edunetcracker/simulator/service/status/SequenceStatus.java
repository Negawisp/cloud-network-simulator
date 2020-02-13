package com.edunetcracker.simulator.service.status;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum SequenceStatus {

    OK("", HttpStatus.OK),
    OWNER_INCONSISTENCY("Owner {} of {} referred to another object.", HttpStatus.FAILED_DEPENDENCY),
    UNEXPECTED_FIELD_VALUE("{} expected in a field {} of a parameter {}", HttpStatus.EXPECTATION_FAILED),
    NULL_POINTER("Method {} got an unexpected null pointer as a parameter {}", HttpStatus.EXPECTATION_FAILED),
    UNTRACKED_DB_OBJECT("{} service did not know about a loaded instance of its underling DBobject.", HttpStatus.EXPECTATION_FAILED),
    DUPLICATE_ID("Detected two different instances of {} with the same ID {}.", HttpStatus.INTERNAL_SERVER_ERROR),
    PARAMETER_TYPE_INCONSISTENCY("Method expected type {} as a parameter.", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_INITIALIZATION("Object of type {} didn't have initial field(s) of: {} filled.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_IN_DATABASE("{} with id {} couldn't have been found in database.", HttpStatus.NO_CONTENT),
    ROUTER_NO_ROUTING_TABLE("Router {} did not yet have a RoutingTable.", HttpStatus.NO_CONTENT),
    ROUTING_TABLE_NO_ROUTER("RoutingTable {} did not have a router.", HttpStatus.NO_CONTENT),
    ROUTER_PORT_NO_IP_ADDRESS("IP address was not yet assigned to RouterPort {}.", HttpStatus.NO_CONTENT),
    ROUTER_PORT_NO_OWNER("RouterPort {} is not assigned to router.", HttpStatus.NO_CONTENT),
    SWITCH_PORT_NO_OWNER("SwitchPort {} is not assigned to switch.", HttpStatus.NO_CONTENT);

/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    private static Logger logger = LoggerFactory.getLogger(SequenceStatus.class);

    private String body;
    private HttpStatus status;

    SequenceStatus(String bodyMessage, HttpStatus status) {
        body = bodyMessage;
        this.status = status;
    }


    public void logError (Object ... params) {
        logger.error(body, params);
    }

    public void logWarning (Object ... params) {
        logger.warn(body, params);
    }

    public void logInfo (Object ... params) {
        logger.info(body, params);
    }

    public HttpStatus getHttpStatus () {
        return status;
    }

    public String getHttpBody () {
        throw new NotImplementedException();
    }

    public String getStringBody (Object ... params) {
        return String.format(body, params);
    }
}
