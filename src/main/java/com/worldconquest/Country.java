package com.worldconquest;

import java.util.ArrayList;


import com.jme3.math.ColorRGBA;

import com.jme3.scene.Geometry;
import com.worldconquest.util.Coordinate;


public class Country {

    ArrayList<City> cities;
    WorldConquest wc;
    int population;
    String name;
    ColorRGBA overlayColor;
    float transparency = 0.1f;
    Geometry countryGeometry;

    public Country(WorldConquest wc, String name) {
        this.wc = wc;
        this.name = name;
        cities = new ArrayList<>();

        initCountry();
    }

    private void initCountry() {

    }
    
    public void formMesh() {
        ArrayList<Coordinate> cityCoordinates = new ArrayList<>();
        for (City city : cities) {
            cityCoordinates.add(city.getCoords());
        }
        
    }

    public void slowUpdate() {
        updatePopulation();
    }

    private void updatePopulation() {
        int popCount = 0;
        for (City city : cities) {
            popCount += city.getPopulation();
        }
        population = popCount;

        
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}
