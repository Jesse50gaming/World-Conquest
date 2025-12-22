package com.worldconquest.buildings;

import com.worldconquest.City;
import com.worldconquest.WorldConquest;

public class BasicOreMine extends Building {
    
    public BasicOreMine(WorldConquest wc, City city) {
        super(wc, city, 50000);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void dailyUpdate() {
        wc.getPlayer().spendMoney(upkeepCost);
        wc.getPlayer().addMoney(1000000); // TODO resources
    }

    
    
}
