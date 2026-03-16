package com.worldconquest.technology;

import java.util.ArrayList;


import com.jme3.math.Vector2f;


public class Tech {

    boolean unlocked = false;
    boolean researched = false;
    String name;
    Vector2f coord;
    //TODO:Add picture later
    ArrayList<Tech> preReqs;

    public Tech(String name,ArrayList<Tech> preReqs, Vector2f coord) {
        this.preReqs = preReqs;
        this.name = name;
        this.coord = coord;
    }

    public boolean unlocked() {
        unlocked = true;
        for (Tech tech : preReqs) {
            if (!tech.researched()) {
                unlocked = false;
            }
        }
        return unlocked;
    }

    public Vector2f getCoord() {
        return coord;
    }

    public void unlock() {
        unlocked = true;
    }
    
    public void lock() {
        unlocked = false;
    }

    public boolean researched() {
        return researched;
    }

    public String getName() {
        return name;
    }

}
