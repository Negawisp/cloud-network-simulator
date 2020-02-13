package com.edunetcracker.simulator.model.dataUnit;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public abstract class DataUnit implements IDataUnit {
    @Setter
    @Getter
    private Type type;

    @Setter
    @Getter
    private String extraData;

    private IDataUnit encapsulatedDataUnit;

    public DataUnit () {
        type = Type.None;
    }

    @Override
    public Optional<IDataUnit> getEncapsulated() {
        return Optional.ofNullable(encapsulatedDataUnit);
    }

    @Override
    public void encapsulate (IDataUnit dataUnit) {
        encapsulatedDataUnit = dataUnit;
    }

    public enum Type {
        None,
        Ethernet,
        IP,
        TCP,
        UDP,
        ICMP
    }
}
