package com.edunetcracker.simulator.service;

import com.edunetcracker.simulator.service.status.SequenceStatus;

public interface CRUDService<T> {

    /**
     * Tries to find an already loaded object.
     * @param id ID of an object to find
     * @return Object with the given ID, or null if fails to find.
     */
    T getLoaded (long id);

    /**
     * First tries to return an already loaded object. If doesn't succeed,
     * loads the instance from the database and returns it instead.
     *
     * @param id ID of a RoutingTable to seek
     * @return RoutingTable with the given ID, or nullPrt, if there is no such ID.
     */
    T get (long id);

    /**
     * Use this when you have created a brand new instance, and you know it has
     * never been in the database, and you want to save it the first time.
     * @param instance Instance to save
     * @return
     */
    SequenceStatus create (T instance);

    /**
     * Use this if you know that the object is already saved to database, and
     * you want to update it there.
     * @param instance Object to update
     * @return This very same instance.
     */
    T update (T instance);

    /**
     * Recursively untracks instance, drops it from the database and unloads it.
     * @param instance
     * @return
     */
    SequenceStatus delete (T instance);
}
