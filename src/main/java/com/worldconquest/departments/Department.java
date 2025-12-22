package com.worldconquest.departments;

import java.util.ArrayList;

import com.worldconquest.Business;
import com.worldconquest.WorldConquest;
import com.worldconquest.buildings.Building;

import de.lessvoid.nifty.elements.Element;


public abstract class Department {

    protected WorldConquest wc;
    DepartmentType departmentType;
    protected int buildingCost;
    String name = "unnamed";
    Element window;
    protected ArrayList<Building> buildings = new ArrayList<>();
    protected Business business;

    public Department(WorldConquest wc, DepartmentType departmentType,String name, int buildingCost, Business business) {
        this.wc = wc;
        this.departmentType = departmentType;
        this.name = name;
        this.buildingCost = buildingCost;
        this.business = business;
        init();
    }

    public void update() {
        doAction();
        updateBuildings();
    }

    public void dailyUpdate() {
        updateDailyBuildings();
    }

    public enum DepartmentType {
        Farming, Extraction, Transportation, Manufacturing,

    }

    private void updateBuildings() {
        for (Building building : buildings) {
            building.update();
        }
    }

    private void updateDailyBuildings() {
        for (Building building : buildings) {
            building.dailyUpdate();
        }
    }

    protected void init() {
        
    }

    protected abstract void doAction();

    public abstract void startBuild();
    public abstract void endBuild();

    public String getName() {
        return name;
    }

    public void setWindow(Element window) {
        this.window = window;
    }

    public void toggleWindow() {
        if (window == null) return;
        if (window.isVisible()) {
            window.hide();
        } else {
            window.show();
        }
    }
    
    public int getBuildingCost() {
        return buildingCost;
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

}
