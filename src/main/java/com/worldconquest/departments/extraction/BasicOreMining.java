package com.worldconquest.departments.extraction;

import com.worldconquest.WorldConquest;
import com.worldconquest.departments.Department;
import com.worldconquest.departments.Department.DepartmentType;

public class BasicOreMining extends Department {

    int cost = 10_000_000;

    public BasicOreMining(WorldConquest wc) {
        super(wc, DepartmentType.Extraction);
        
    }

    @Override
    protected void doAction() {
        
    }



    @Override
    protected void create() {
        
    }

}
