package com.worldconquest.technology;


import java.util.HashMap;
import java.util.Vector;

public class Tech {

    private boolean researched = false;
    private int level = 0;
    private HashMap<Tech, Integer> preReqs;
    private Vector2f coords;

    public Tech(HashMap<Tech, Integer> preReqs) {
        this.preReqs = preReqs;
    }

    public boolean isResearched() {
        return researched;
    }

    public void setCoord(Vector2f coords) {
        this.coords = coords;
    }

    public Vector2f getCoord() {
        return coords;
    }
        

    public boolean researchable() {
        for (Tech tech : preReqs.keySet()) {
            if (!tech.isResearched() || tech.getLevel() < preReqs.get(tech)) {
                return false;
            }
        }
        return true;
    }

    public int getLevel() {
        return level;
    }

}
