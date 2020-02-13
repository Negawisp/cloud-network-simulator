package com.edunetcracker.simulator.database.repository;

import com.edunetcracker.simulator.model.Scene;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SceneRepository extends CrudRepository<Scene, Long> {

// Methods to find a set of objects

    List<Scene> findAll ();
//ToDo: Finding scenes by user
// List<Scene> findAllByUser (User user);


// Methods to find a single object

    Optional<Scene> findById (Long id);
    Optional<Scene> findByName (String name);


// Methods to save / remove object(s)

    Scene save (Scene entity);

    void deleteById (Long id);
    void deleteByName (String name);

//ToDo: deleting scenes by user
//  void deleteAllByUser(User user);
}
