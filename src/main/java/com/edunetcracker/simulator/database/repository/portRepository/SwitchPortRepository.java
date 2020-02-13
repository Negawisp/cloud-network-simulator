package com.edunetcracker.simulator.database.repository.portRepository;

import com.edunetcracker.simulator.model.element.Switch;
import com.edunetcracker.simulator.model.port.SwitchPort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SwitchPortRepository extends CrudRepository<SwitchPort, Long> {

// Methods to find a set of objects

    List<SwitchPort> findAll();
    List<SwitchPort> findAllBySwitchEl(Switch switchEl);


// Methods to find a single object

    Optional<SwitchPort> findById(Long id);
    Optional<SwitchPort> findByMac(long mac);


// Methods to save / remove object(s)

    SwitchPort save (SwitchPort entity);

    void deleteById (Long id);
    void deleteByMac (Long Mac);

    void deleteAllBySwitchEl(Switch switchEl);
}
