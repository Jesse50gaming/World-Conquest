package com.worldconquest.buildings;

import com.worldconquest.City;
import com.worldconquest.WorldConquest;

public abstract class Building {
    protected int upkeepCost;
    protected City city;
    protected WorldConquest wc;

    public Building(WorldConquest wc,City city, int upkeepCost) {
        this.city = city;
        this.upkeepCost = upkeepCost;
    }

    public abstract void update();

    public abstract void dailyUpdate();


}
