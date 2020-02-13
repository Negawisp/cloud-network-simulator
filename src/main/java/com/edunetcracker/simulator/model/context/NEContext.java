package com.edunetcracker.simulator.model.context;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;

import java.util.List;

public interface NEContext {

    long getId();

    /**
     * Checks whether inner context's timer is run out
     */
    boolean isAlive();

    /**
     * Performs action
     * @return A set of DataUnit's to be sent somewhere else
     */
    List<DataUnit> performAction();

    /**
     * Is called on the stage when the input is being processed
     * changes context's inner variables
     * @param pack - a package to analyse
     */
    boolean performInput(DataUnit pack);

    //TODO(Wisp): Comparator for contexts
    boolean equals (NEContext other);
}
