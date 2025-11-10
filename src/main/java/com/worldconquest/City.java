package com.worldconquest;


import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.worldconquest.util.Coordinate;

public class City {

    WorldConquest wc;
    int population;
    Coordinate coordinates;
    String name;
    Geometry cityGeometry;
    String countryName;

    public City(WorldConquest wc, float lat, float lon, int population, String name, String countryName) {
        this.wc = wc;
        this.population = population;
        this.name = name;
        this.countryName = countryName;
        coordinates = new Coordinate(lat, lon, wc);

        initCity();
    }
    
    private void initCity() {

        float heightOffset;

        if (population < 1000000) {
            float cylRadius = 0.2f;
            float cylHeight = 0.2f;
            heightOffset = cylHeight / 2f;

            Cylinder cylinder = new Cylinder(16, 16, cylRadius, cylHeight, true);
            cityGeometry = new Geometry(name, cylinder);

            //point out
            Vector3f pos = coordinates.coordinateToVector();
            Vector3f direction = pos.normalize();
            cityGeometry.lookAt(pos.add(direction), Vector3f.UNIT_Y);

            // move to surface
            cityGeometry.setLocalTranslation(pos.add(direction.mult(heightOffset)));

        } else {
            Box box = new Box(0.4f, 0.4f, 0.2f);
            cityGeometry = new Geometry(name, box);

            Vector3f pos = coordinates.coordinateToVector();

            Vector3f direction = pos.normalize();
            cityGeometry.lookAt(pos.add(direction), Vector3f.UNIT_Y);

            heightOffset = 0.2f; // half box height
            cityGeometry.setLocalTranslation(pos.add(pos.normalize().mult(heightOffset)));
        }

        // Material
        Material mat = new Material(wc.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        cityGeometry.setMaterial(mat);
        cityGeometry.setUserData("cityName", name);
        cityGeometry.setUserData("population", population);
        cityGeometry.setUserData("countryName", countryName);
        wc.getRootNode().attachChild(cityGeometry);

        
    }
    
    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public Geometry getCityGeometry() {
        return cityGeometry;
    }

}
