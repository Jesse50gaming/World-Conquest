package com.worldconquest.departments;

import com.worldconquest.WorldConquest;

public abstract class Department {

    WorldConquest wc;
    DepartmentType departmentType;

    public Department(WorldConquest wc, DepartmentType departmentType) {
        this.wc = wc;
        this.departmentType = departmentType;
        init();
    }

    public void update() {
        doAction();
    }

    public enum DepartmentType {
        Farming, Extraction, Transportation, Manufacturing,

    }

    protected void init() {
        createDepartment();
    }

    protected abstract void doAction();

    protected abstract void createDepartment();

    

}
