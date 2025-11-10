package com.worldconquest;

import java.util.ArrayList;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Earth {

    WorldConquest wc;

    public float radius = 100f;
    private Spatial earthSpatial;

    ArrayList<City> cities;

    public Earth(WorldConquest wc) {
        this.wc = wc;
        cities = new ArrayList<City>();
        initEarth();
    }

    public void initEarth() {
        earthSpatial = wc.getAssetManager().loadModel("Models/Earth/Earth_1_12756.glb");

        BoundingBox bb = (BoundingBox) earthSpatial.getWorldBound(); 
        float maxExtent = Math.max(bb.getXExtent(), Math.max(bb.getYExtent(), bb.getZExtent()));
        float scaleFactor = radius / maxExtent;
        earthSpatial.scale(scaleFactor);
        earthSpatial.rotate(0, FastMath.DEG_TO_RAD * -90, 0);
        Vector3f center = bb.getCenter();
        earthSpatial.setLocalTranslation(earthSpatial.getLocalTranslation().subtract(center));
        wc.getRootNode().attachChild(earthSpatial);

        
    }
    
    public Spatial getEarthSpatial() {
        return earthSpatial;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    public float getRadius() {
        return radius;
    }
    
    
}
