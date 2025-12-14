package com.worldconquest.departments;

import com.worldconquest.WorldConquest;

public abstract class Department {

    WorldConquest wc;
    DepartmentType departmentType;

    String name = "unnamed";

    public Department(WorldConquest wc, DepartmentType departmentType,String name) {
        this.wc = wc;
        this.departmentType = departmentType;
        this.name = name;
        init();
    }

    public void update() {
        doAction();
    }

    public enum DepartmentType {
        Farming, Extraction, Transportation, Manufacturing,

    }

    protected void init() {
        
    }

    protected abstract void doAction();

    protected abstract void create();

    public String getName() {
        return name;
    }

}
