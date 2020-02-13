package com.edunetcracker.simulator.service;


public abstract class DBService<T> implements CRUDService<T> {

    /**
     * Adds an instance to the service's track list.
     * (May come in handy, dunno)
     * @param instance
     */
    abstract protected void addToLoaded(T instance);

    /**
     * Removes the object from the service's track list.
     * @param instance Object to 'unload'.
     */
    abstract protected void unload (T instance);

    /**
     * Drops the instance's representation from the database.
     * @param instance Object to delete from database.
     */
    abstract protected void drop(T instance);
}
