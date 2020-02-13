package com.edunetcracker.simulator.model.context;

import com.edunetcracker.simulator.model.dataUnit.DataUnit;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TrafficContext implements NEContext {

    private GeneratedConfig trafficConfig;
    private long send;

    //the interval between which we call the function once
    static final long TIME_TACT = 100; //todo: find a proper value

    //receive object, generator configuration
    //later: add different types of generator (like dataunit)
    public TrafficContext(GeneratedConfig trafficInit){
        send = 0;
        this.trafficConfig = trafficInit;
    }

    @Override
    public long getId() {
        return trafficConfig.getId();
    }

    @Override
    public boolean isAlive() {
        return trafficConfig.isAlive();
    }

    @Override
    public List<DataUnit> performAction() {
        List<DataUnit> contextList = new LinkedList<>();

        send += TIME_TACT;

        if (trafficConfig.getTrafficType() == TrafficType.SIMPLE) {
            for(int i = 0; i < send / trafficConfig.getTimeBetPack(); i++) {
                DataUnit dataUnit = contextBuilder(DataUnit.Type.UDP, null);
                contextList.add(dataUnit);
            }

            send = send % trafficConfig.getTimeBetPack();
        }

        return contextList;
    }

    @Override
    public boolean performInput(DataUnit pack) {
        //is not needed for this function
        return false;
    }

    @Override
    public boolean equals(NEContext another) {
        if (null == another) {
            return false;
        }
        return trafficConfig.getId() == another.getId();
    }

    public static DataUnit contextBuilder (DataUnit.Type type, String extraData) {
        DataUnit newPacket = new DataUnit(){
            @Override
            public void setType(Type type) {
                super.setType(type);
            }

            @Override
            public void setExtraData(String extraData) {
                super.setExtraData(extraData);
            }
        };

        return newPacket;
    }

    public enum TrafficType {
        SIMPLE,
        EXPONENTIAL,
        POISSON

    }

}
