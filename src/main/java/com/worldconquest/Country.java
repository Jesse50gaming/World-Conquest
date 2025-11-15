package com.worldconquest;

import java.util.ArrayList;

public class Country {

    ArrayList<City> cities;
    WorldConquest wc;
    int population;
    String name;

    public Country(WorldConquest wc, String name) {
        this.wc = wc;
        this.name = name;
        cities = new ArrayList<>();
    }



    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
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





}
