package com.edunetcracker.simulator.service;

import com.edunetcracker.simulator.model.element.NetworkElement;
import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.model.port.Port;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Random;

@Service
public class NetworkService {
    private static Logger logger = LoggerFactory.getLogger(NetworkService.class);

    @Value("${nc_project.ieee.org-id}")
    private String ieeeOrgIdStr;
    private long ieeeOrgId;

    private Random random = new Random();

    @PostConstruct
    private void initIeeeOrgId () {
        ieeeOrgId = Long.parseLong(ieeeOrgIdStr, 16);
        logger.info(String.format("IEEE org ID: %x", ieeeOrgId));
    }

    public Port giveMacToPort (Port port) {
        port.setMac(ieeeOrgId + random.nextInt(0xFFFFFF));
        return port;
    }
}
