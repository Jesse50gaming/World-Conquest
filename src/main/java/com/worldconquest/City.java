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
    Country country;
    ColorRGBA color;

    public City(WorldConquest wc, float lat, float lon, int population, String name, Country country) {
        this.wc = wc;
        this.population = population;
        this.name = name;
        this.country = country;
        coordinates = new Coordinate(lat, lon, wc);
        initCity();
        color = new ColorRGBA(country.getColor());
    }
    
    private void initCity() {
        
        float heightOffset;

        if (population < 1000000) {
            float maxR = 1f;
            float minR = 0.2f;
            float cylHeight = 1f;
            heightOffset = cylHeight / 2f;

            float minPop = 50000f;
            float maxPop = 1000000f;
            float normalized = (population - minPop) / (maxPop - minPop);
            normalized = Math.max(0, Math.min(1, normalized));

            float cylRadius = minR + normalized * (maxR - minR);

            Cylinder cylinder = new Cylinder(16, 16, cylRadius, cylHeight * specialCountryHeightMult(country.getName()), true);
            cityGeometry = new Geometry(name, cylinder);

            Vector3f pos = coordinates.coordinateToVector();
            Vector3f direction = pos.normalize();
            cityGeometry.lookAt(pos.add(direction), Vector3f.UNIT_Y);
            cityGeometry.setLocalTranslation(pos.add(direction.mult(heightOffset)));
        } else {
            float maxS = 3f;
            float minS = 1f;
            float boxHeight = 1f;
            float minPop = 1000000f;
            float maxPop = 20000000f;
            float normalized = (population - minPop) / (maxPop - minPop);
            normalized = Math.max(0, Math.min(1, normalized));
            float sideLength = minS + normalized * (maxS - minS);

            Box box = new Box(sideLength, sideLength, boxHeight * specialCountryHeightMult(country.getName()));
            cityGeometry = new Geometry(name, box);

            Vector3f pos = coordinates.coordinateToVector();

            Vector3f direction = pos.normalize();
            cityGeometry.lookAt(pos.add(direction), Vector3f.UNIT_Y);

            heightOffset = boxHeight / 2;
            cityGeometry.setLocalTranslation(pos.add(pos.normalize().mult(heightOffset)));
        }
        
        // Material
        Material mat = new Material(wc.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        cityGeometry.setMaterial(mat);
        cityGeometry.setUserData("cityName", name);
        cityGeometry.setUserData("population", population);
        cityGeometry.setUserData("countryName", country.getName());
        wc.getRootNode().attachChild(cityGeometry);

    }
    
    private float specialCountryHeightMult(String country) {
        if(country.equals("Japan")) {
            return 3;
        } else if (country.equals("Argentina")) {
            return 2;
        }
        return 1;
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

    public Coordinate getCoords() {
        return coordinates;
    }

}
