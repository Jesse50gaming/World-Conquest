package com.worldconquest.departments;

import com.worldconquest.WorldConquest;

public abstract class Department {

    WorldConquest wc;
    DepartmentType departmentType;

    public Department(WorldConquest wc, DepartmentType departmentType) {
        this.wc = wc;
        this.departmentType = departmentType;
    }

    public void update() {
        doAction();
    }

    protected enum DepartmentType {
        Farming,
        Extraction,
        Transportation,
        Manufacturing,

    }

    protected abstract void doAction();

    

}
