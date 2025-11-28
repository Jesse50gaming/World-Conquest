package com.worldconquest;


import java.util.ArrayList;

import com.jme3.math.ColorRGBA;
import com.jme3.texture.Image;
import com.worldconquest.util.ColorHelper;


public class Country {

    ArrayList<City> cities;
    WorldConquest wc;
    int population;
    String name;
    ColorRGBA color;
    ColorHelper colorHelper = new ColorHelper();
    Image flag;


    public Country(WorldConquest wc, String name) {
        this.wc = wc;
        this.name = name;
        cities = new ArrayList<>();
        
        initCountry();
    }

    private void initCountry() {
        
        color = new ColorRGBA(colorHelper.getCountryColor(name));
        
            
        
    }
    

    public void dailyUpdate() {
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

    public ColorRGBA getColor() {
        
        return color;
    }

    

}
