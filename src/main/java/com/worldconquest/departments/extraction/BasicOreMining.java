package com.worldconquest.departments.extraction;

import java.util.ArrayList;

import com.worldconquest.City;
import com.worldconquest.WorldConquest;
import com.worldconquest.buildings.BasicOreMine;
import com.worldconquest.controls.PlayerInput.MouseStates;
import com.worldconquest.departments.Department;
import com.worldconquest.departments.Department.DepartmentType;

public class BasicOreMining extends Department {

    int cost = 10_000_000;
    

    public BasicOreMining(WorldConquest wc) {
        super(wc, DepartmentType.Extraction, "Basic Ore Mining");
        building = new BasicOreMine();
        
    }

    @Override
    protected void doAction() {
        
    }



    @Override
    public void create() {
        
        wc.getPlayerInput().startCitySelection(1, 3);
        
    }

    public void endCreate() {
        ArrayList<City> cities;
        cities = wc.getPlayerInput().getSelectedCities();
        wc.getPlayerInput().endCitySelection();

        for (City city : cities) {
            
        }


    }


}
