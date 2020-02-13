package com.edunetcracker.simulator.database.repository.portRepository;

import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.model.port.RouterPort;
import com.edunetcracker.simulator.model.port.SwitchPort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RouterPortRepository extends CrudRepository<RouterPort, Long> {

// Methods to find a set of objects

    List<RouterPort> findAll ();
    List<RouterPort> findAllByRouter (Router router);


// Methods to find a single object

    Optional<RouterPort> findById (Long id);
    Optional<RouterPort> findByMac (Long mac);
    Optional<RouterPort> findByIp (Integer ip);

// Methods to save / remove object(s)

    SwitchPort save (SwitchPort entity);

    void deleteById (Long id);
    void deleteByMac (Long mac);
    void deleteByIp (Integer ip);

    void deleteAllByRouter (Router router);
}
