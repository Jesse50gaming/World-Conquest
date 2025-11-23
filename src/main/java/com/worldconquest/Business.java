package com.worldconquest;

import java.util.ArrayList;

import com.worldconquest.departments.Department;

public class Business {

    WorldConquest wc;
    int money;
    String name;
    ArrayList<Department> departments;

    public Business(String name, WorldConquest wc) {
        this.wc = wc;
        this.name = name;
        departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    


}
