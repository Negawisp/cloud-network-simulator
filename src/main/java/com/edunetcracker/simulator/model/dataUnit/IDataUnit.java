package com.edunetcracker.simulator.model.dataUnit;

import java.util.Optional;

public interface IDataUnit {

    Optional<IDataUnit> getEncapsulated();

    void encapsulate (IDataUnit dataUnit);
}
