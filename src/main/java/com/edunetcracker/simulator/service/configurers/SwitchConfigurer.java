package com.edunetcracker.simulator.service.configurers;

import com.edunetcracker.simulator.model.element.Switch;
import com.edunetcracker.simulator.model.port.SwitchPort;
import com.edunetcracker.simulator.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SwitchConfigurer {

    Logger logger = LoggerFactory.getLogger(SwitchConfigurer.class);

    @Autowired
    NetworkService networkService;

    public void givePortsTo (Switch switchEl) {
        assert switchEl != null;

        if (switchEl.getPhysConfigType() == null) {
            logger.warn("Switch with id \"{}\" didn't have a physical configuration type.", switchEl.getIdNE());
            return;
        }

        giveLazyPortsTo(switchEl);
    }

    /**
     * Gives 4 ports to switchEl.
     * @param switchEl Switch to give ports to.
     */
    private void giveLazyPortsTo (Switch switchEl) {
        assert switchEl != null;

        List<SwitchPort> ports = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            SwitchPort nextPort = new SwitchPort();
            networkService.giveMacToPort(nextPort);
            nextPort.setSwitchEl(switchEl);

            ports.add(nextPort);
        }

        switchEl.setPorts(ports);
    }
}
