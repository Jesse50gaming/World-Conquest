package com.worldconquest.technology;


import java.util.HashMap;

public class Tech {

    private boolean researched = false;
    private int level = 0;
    private HashMap<Tech, Integer> preReqs;

    public Tech(HashMap<Tech, Integer> preReqs) {
        this.preReqs = preReqs;
    }

    public boolean isResearched() {
        return researched;
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
