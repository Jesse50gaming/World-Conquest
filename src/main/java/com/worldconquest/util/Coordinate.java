package com.worldconquest.util;

import com.jme3.math.Vector3f;
import com.worldconquest.WorldConquest;
import com.jme3.math.FastMath;

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

    public Vector3f coordinateToVector() {
        // Convert degrees to radians
        float latRad = FastMath.DEG_TO_RAD * lat;
        float lonRad = FastMath.DEG_TO_RAD * lon;

        float x = radius * FastMath.cos(latRad) * FastMath.cos(lonRad);
        float y = radius * FastMath.sin(latRad);
        float z = radius * FastMath.cos(latRad) * FastMath.sin(lonRad);

        return new Vector3f(x, y, z);
    }
}
