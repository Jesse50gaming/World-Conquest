package com.worldconquest.buildings;

import com.worldconquest.Business;
import com.worldconquest.City;
import com.worldconquest.WorldConquest;
import com.worldconquest.departments.Department;

public class BasicOreMine extends Building {

    public BasicOreMine(WorldConquest wc, City city, Business business, Department department) {
        super(wc, city, 50000, business, "Basic Ore Mine", department);
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
