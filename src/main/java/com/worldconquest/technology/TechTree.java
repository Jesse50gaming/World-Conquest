package com.worldconquest.technology;

import java.util.ArrayList;

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
        
    }
}
