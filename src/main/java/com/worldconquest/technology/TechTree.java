package com.worldconquest.technology;


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
        
    }
    public void addTechs(ArrayList<Tech> techs) {
        this.techs.addAll(techs);
        
    }
   
}
