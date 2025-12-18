package com.worldconquest.departments.extraction;

import com.worldconquest.WorldConquest;
import com.worldconquest.controls.PlayerInput.MouseStates;
import com.worldconquest.departments.Department;
import com.worldconquest.departments.Department.DepartmentType;

public class BasicOreMining extends Department {

    int cost = 10_000_000;

    public BasicOreMining(WorldConquest wc) {
        super(wc, DepartmentType.Extraction, "Basic Ore Mining");
        
    }

    @Override
    protected void doAction() {
        
    }



    @Override
    public void create() {
       
        wc.getPlayerInput().startCitySelection(1,3);
    }

}
