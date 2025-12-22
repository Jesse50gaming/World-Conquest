package com.worldconquest.departments;

import java.util.ArrayList;

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

    public Department(WorldConquest wc, DepartmentType departmentType,String name, int buildingCost) {
        this.wc = wc;
        this.departmentType = departmentType;
        this.name = name;
        this.buildingCost = buildingCost;
        init();
    }

    public void update() {
        doAction();
        updateBuildings();
    }

    public void dailyUpdate() {
        updateBuildings();
    }

    public enum DepartmentType {
        Farming, Extraction, Transportation, Manufacturing,

    }

    private void updateBuildings() {
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

}
