package com.edunetcracker.simulator.database.repository.networkElementRepository;

import com.edunetcracker.simulator.model.Scene;
import com.edunetcracker.simulator.model.element.Switch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SwitchRepository extends CrudRepository<Switch, Long> {

// Methods to find a set of objects

    List<Switch> findAll();
    List<Switch> findAllByScene(Scene scene);


// Methods to find a single object

    Optional<Switch> findByXAndY(Long x, Long y);
    Optional<Switch> findByIdNE(Long id);


// Methods to save / remove object(s)

    Switch save (Switch entity);

    void deleteById (Long id);

    void deleteAllByScene(Scene scene);
}
