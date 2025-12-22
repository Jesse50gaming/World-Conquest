package com.worldconquest.buildings;

import com.worldconquest.Business;
import com.worldconquest.City;
import com.worldconquest.WorldConquest;
import com.worldconquest.departments.Department;

public abstract class Building {
    protected int upkeepCost;
    protected City city;
    protected WorldConquest wc;
    protected Business business;
    protected String name;
    protected Department department;

    public Building(WorldConquest wc,City city, int upkeepCost, Business business, String name, Department department) {
        this.wc = wc;
        this.city = city;
        this.upkeepCost = upkeepCost;
        this.business = business;
        this.name = name;
        this.department = department;
    }

    public abstract void update();

    public abstract void dailyUpdate();

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }


}
