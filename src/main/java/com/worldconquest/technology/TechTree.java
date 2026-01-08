package com.worldconquest.technology;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.jme3.math.Vector2f;

public class TechTree {

    private ArrayList<Tech> techs;
   

    public TechTree(ArrayList<Tech> techs) {
        this.techs = techs;
    }

    public void addTech(Tech tech) {
        techs.add(tech);
        updatePosition();
    }

    public void addTechs(ArrayList<Tech> techs) {
        this.techs.addAll(techs);
    }

    private void updatePosition() {
        
        ArrayList<Tech> locationNotSet = new ArrayList<>();
        locationNotSet.addAll(techs);
        Tech rootTech = null;
        HashMap<Tech, Integer> techDepth = new HashMap<>();

        
        //find all depths and root
        for (Tech tech : locationNotSet) {
            int depth = findDepth(tech);
            techDepth.put(tech, depth);
            if (depth == 0) {
                rootTech = tech;
                rootTech.setCoord(new Vector2f(0,0));
            }
            
        }
        
        //set in relevance to root

        for (Tech tech : techs) {

        }

    }
    //TODO most likely an error
    private int findDepth(Tech tech) {
        int depth = -1;
        for (Tech preReq : tech.getPreReqs().keySet()) {

            depth = Math.max(depth, 1 + findParentDepth(preReq));
        }

        return depth;

    }
    
    private int findParentDepth(Tech tech) {
        if (tech.getPreReqs().isEmpty() || tech.getPreReqs() == null) {
            return 0;
        }
        
        return findDepth(tech);
    }
}
