package com.edunetcracker.simulator.database.repository.routingRepository;


import com.edunetcracker.simulator.model.routing.routingTableEntry.RoutingTableEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoutingTableEntryRepository extends CrudRepository<RoutingTableEntry, Long> {


// Methods to find a set of objects

    List<RoutingTableEntry> findAll();

// Methods to find a single object

    Optional<RoutingTableEntry> findById(Long id);

// Methods to save / remove object(s)

    RoutingTableEntry save (RoutingTableEntry entity);

    void deleteById (Long id);

    void  deleteAllByRouteSource(char routeSource);

    void deleteAllByRouteSourceAndNextHop(char routeSource, long nextHop);
}
