package com.worldconquest.departments;

import com.worldconquest.WorldConquest;

import de.lessvoid.nifty.controls.Window;
import de.lessvoid.nifty.elements.Element;


public abstract class Department {

    WorldConquest wc;
    DepartmentType departmentType;

    String name = "unnamed";
    Element window;

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
