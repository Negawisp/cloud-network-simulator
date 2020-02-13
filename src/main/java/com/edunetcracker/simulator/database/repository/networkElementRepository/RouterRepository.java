package com.edunetcracker.simulator.database.repository.networkElementRepository;

import com.edunetcracker.simulator.model.Scene;
import com.edunetcracker.simulator.model.element.Router;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RouterRepository extends CrudRepository<Router, Long> {

// Methods to find a set of objects

    List<Router> findAll();
    List<Router> findAllByScene(Scene scene);


// Methods to find a single object

    Optional<Router> findByXAndY(long x, long y);
    Optional<Router> findByIdNE(Long id);


// Methods to save / remove object(s)

    Router save (Router entity);

    void deleteById (Long id);

    void deleteAllByScene (Scene scene);
}
