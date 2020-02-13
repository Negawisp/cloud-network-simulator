package com.edunetcracker.simulator.database.repository.routingRepository;

import com.edunetcracker.simulator.model.element.Router;
import com.edunetcracker.simulator.model.routing.routingTable.RoutingTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoutingTableRepository extends CrudRepository<RoutingTable, Long> {

// Methods to find a set of objects

    List<RoutingTable> findAll();


// Methods to find a single object

    Optional<RoutingTable> findByRouter(Router router);

// Methods to save / remove object(s)

    RoutingTable save (RoutingTable routingTable);

    void deleteById (Long id);

    void deleteAllByRouter (Router router);
}
