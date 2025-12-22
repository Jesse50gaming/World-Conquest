package com.worldconquest.departments.extraction;

import java.util.ArrayList;

import com.worldconquest.City;
import com.worldconquest.WorldConquest;
import com.worldconquest.buildings.BasicOreMine;
import com.worldconquest.departments.Department;
import com.worldconquest.departments.Department.DepartmentType;

public class BasicOreMining extends Department {

    
    

    public BasicOreMining(WorldConquest wc) {
        super(wc, DepartmentType.Extraction, "Basic Ore Mining",10000000);
        
       
        
    }

    @Override
    protected void doAction() {
        
    }



    @Override
    public void startBuild() {

        wc.getPlayerInput().startCitySelection(1, 10000, this);

    }

    @Override
    public void endBuild() {
        ArrayList<City> cities;
        cities = wc.getPlayerInput().getSelectedCities();
        wc.getPlayerInput().endCitySelection();

        for (City city : cities) {
            BasicOreMine mine = new BasicOreMine(wc, city);
            buildings.add(mine);
            city.addBuilding(mine);
        }
    }

}
