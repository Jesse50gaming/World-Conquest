package com.worldconquest;

import com.worldconquest.departments.Department;

public class Player extends Business{

    public Player(String name, WorldConquest wc) {
        super(name, wc);
        money = 1_000_000_000;
    }
    
    @Override
    public void addDepartment(Department department) {
        departments.add(department);
        wc.getGui().updateDepartments(department);
    }

}
