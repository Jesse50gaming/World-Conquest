package com.worldconquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Earth {

    WorldConquest wc;

    public float radius = 500f;
    private Spatial earthSpatial;

    ArrayList<City> cities;
    ArrayList<Country> countries;

    public Earth(WorldConquest wc) {
        this.wc = wc;
        cities = new ArrayList<>();
        countries = new ArrayList<>();
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
    
    public void loadCitiesFromGeoNames(int minPopulation) {
        HashMap<String, Country> countryHashMap = new HashMap<>();
        InputStream geoNamesStream = getClass().getResourceAsStream("/Data/cities/geonames-all-cities-with-a-population-1000.csv");
        
        if (geoNamesStream == null) {
            System.err.println("GeoNames city file not found in resources!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(geoNamesStream))) { // countries
            String line;
            boolean firstLine = true;
            

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(";");
                if (parts.length < 20) continue;

               
                String countryName = parts[7].trim();

                countryHashMap.put(countryName, new Country(wc, countryName));
            }

            countries.addAll(countryHashMap.values());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(geoNamesStream))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(";");
                if (parts.length < 20) continue;

                String name = parts[1].trim();
                String countryName = parts[7].trim();
                String populationStr = parts[13].trim();
                String coordinates = parts[19].trim();

                if (populationStr.isEmpty() || coordinates.isEmpty()) continue;

                int population;
                try {
                    population = Integer.parseInt(populationStr);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (population < minPopulation) continue;

                String[] coords = coordinates.split(",");
                if (coords.length != 2) continue;

                float latitude, longitude;
                try {
                    latitude = Float.parseFloat(coords[0]);
                    longitude = Float.parseFloat(coords[1]);
                } catch (NumberFormatException e) {
                    continue;
                }

                /* 
                if (countryName.equals("Japan")) {
                    System.out.println(name + ", " + countryName + " - " + population);
                }
                */

                City city = new City(wc, latitude, longitude, population, name, countryName);
                addCity(city);
                countryHashMap.get(countryName).addCity(city);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
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
