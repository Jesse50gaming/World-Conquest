package com.worldconquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Earth {

    WorldConquest wc;

    public float radius = 500f;
    private Spatial earthSpatial;

    
    ArrayList<Country> countries;

    public Earth(WorldConquest wc) {
        this.wc = wc;
        
        countries = new ArrayList<>();
        initEarth();
    }

    public void update() {

    }

    public void slowUpdate() {
        for (Country country : countries) {
            country.slowUpdate();
        }
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
    
    public void loadCitiesFromGeoNames(int minPopulation) {
        InputStream geoNamesStream = getClass().getResourceAsStream(
                "/Data/cities/geonames-all-cities-with-a-population-1000.csv");

        if (geoNamesStream == null) {
            System.err.println("GeoNames city file not found in resources!");
            return;
        }

        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(geoNamesStream))) {
            lines = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // countries
        HashMap<String, Country> countryHashMap = new HashMap<>();

        boolean first = true;
        for (String line : lines) {
            if (first) { first = false; continue; }

            String[] parts = line.split(";");
            if (parts.length < 20) continue;

            String countryName = parts[7].trim();

            if (!countryHashMap.containsKey(countryName)) {
                countryHashMap.putIfAbsent(countryName, new Country(wc, countryName));
            }
            
        }

        countries.addAll(countryHashMap.values());

        // cities
        first = true;
        for (String line : lines) {
            if (first) {
                first = false;
                continue;
            }

            String[] parts = line.split(";");
            if (parts.length < 20) continue;

            String name = parts[1].trim();
            String countryName = parts[7].trim();
            String populationStr = parts[13].trim();
            String coordStr = parts[19].trim();

            if (populationStr.isEmpty() || coordStr.isEmpty()) continue;

            int population;
            try {
                population = Integer.parseInt(populationStr);
            } catch (NumberFormatException e) {
                continue;
            }

            if (population < minPopulation) continue;

            String[] coords = coordStr.split(",");
            if (coords.length != 2) continue;

            float lat, lon;
            try {
                lat = Float.parseFloat(coords[0]);
                lon = Float.parseFloat(coords[1]);
            } catch (NumberFormatException e) {
                continue;
            }

            City city = new City(wc, lat, lon, population, name, countryHashMap.get(countryName));
            countryHashMap.get(countryName).addCity(city);
        }
    }
    
    public Spatial getEarthSpatial() {
        return earthSpatial;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
    }

    public float getRadius() {
        return radius;
    }
    
    
}
