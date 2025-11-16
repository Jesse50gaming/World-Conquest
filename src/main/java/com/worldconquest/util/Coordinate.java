package com.worldconquest.util;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.worldconquest.WorldConquest;

public class Coordinate {

    float lat, lon; // in degrees
    WorldConquest wc;
    float radius;

    public Coordinate(float lat, float lon, WorldConquest wc) {
        this.lat = lat;
        this.lon = lon;
        this.wc = wc;

        radius = wc.getEarth().getRadius();
    }

    public Coordinate(Vector2f vector, WorldConquest wc) {
        this.lat = vector.x;
        this.lon = vector.y;
        this.wc = wc;

        radius = wc.getEarth().getRadius();
    }

    public Vector3f coordinateToVector() {
        float latRad = (float) Math.toRadians(lat);
        float lonRad = (float) -Math.toRadians(lon);

        float x = (float) (radius * Math.cos(latRad) * Math.cos(lonRad));
        float y = (float) (radius * Math.sin(latRad));
        float z = (float) (radius * Math.cos(latRad) * Math.sin(lonRad));

        return new Vector3f(x, y, z);
    }
    
    public Vector2f getVector() {
        return new Vector2f(lat,lon);
    }

   
    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
