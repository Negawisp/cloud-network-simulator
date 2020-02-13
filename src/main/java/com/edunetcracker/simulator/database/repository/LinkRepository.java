package com.edunetcracker.simulator.database.repository;

import com.edunetcracker.simulator.model.Link;
import com.edunetcracker.simulator.model.Scene;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, Long> {

// Methods to find a set of objects

    List<Link> findAll ();
    List<Link> findAllByScene (Scene scene);


// Methods to find a single object

    Optional<Link> findById (Long id);

    /**
     * Keep in mind that THE ORDER OF THE PORTS <b>MATTERS</b>!
     * @param portAid id of the portA in the database
     * @param portZid id of the portZ in the database
     * @return Optional of Link, connecting those two ports.
     */
    Optional<Link> findByPortAidOrPortZid (Long portAid, Long portZid);
    Optional<Link> findByPortAid (Long portAid);
    Optional<Link> findByPortZid (Long portZid);


// Methods to save / remove object(s)

    Link save (Link entity);

    void deleteById (Long id);
    void deleteByPortAidAndPortZid (Long portAid, Long portZid);

    void deleteAllByScene (Scene scene);
}
