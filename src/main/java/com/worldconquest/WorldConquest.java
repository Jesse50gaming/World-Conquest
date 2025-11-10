package com.worldconquest;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jme3.app.SimpleApplication;

import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;
import com.worldconquest.controls.OrbitCamera;

public class WorldConquest extends SimpleApplication {

    public Earth earth;
    private OrbitCamera orbitCamera;

    public static void main(String[] args) {
        WorldConquest app = new WorldConquest();

        AppSettings settings = new AppSettings(true);
        settings.setFullscreen(true);

        // Use system resolution safely
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        settings.setResolution(mode.getWidth(), mode.getHeight());

        settings.setResizable(false);
        settings.setTitle("World Conquest");
        app.setDisplayStatView(false);
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
        
    }

    @Override
    public void simpleInitApp() {

        earth = new Earth(this);
        initLight();
        initCamera();
        initCities();

        /*  test group
        City city = new City(this, 51.5072f, 0.1276f, 100, "london");
        earth.addCity(city);
        City city1 = new City(this, 41.8967f, 12.4822f, 1000, "Rome");
        earth.addCity(city1);
        City city2 = new City(this, 40.71f, -74f, 1000000, "DC");
        earth.addCity(city2);
        */
    }
    
    private void initCities() {
        InputStream is = getClass().getResourceAsStream("/Data/cities/1000-cities.csv");
        if (is == null) {
            System.out.println("File not found in resources!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                float lat = Float.parseFloat(parts[1].trim());
                float lon = Float.parseFloat(parts[2].trim());
                String name = parts[3].trim();
                
                int population = Integer.parseInt(parts[5].trim());

                City city = new City(this, lat, lon, population, name);
                earth.addCity(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    

    private void initCamera() {
        orbitCamera = new OrbitCamera(cam, inputManager, earth.getEarthSpatial());
        inputManager.setCursorVisible(true);
        orbitCamera.updateCamera();
    }

    private void initLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, 0, -1).normalizeLocal());
        sun.setColor(ColorRGBA.White.mult(1f));  
        rootNode.addLight(sun);

        DirectionalLight moon = new DirectionalLight();
        moon.setDirection(new Vector3f(1, 0, 1).normalizeLocal());
        moon.setColor(ColorRGBA.White.mult(1f)); 
        rootNode.addLight(moon);

        DirectionalLight fill = new DirectionalLight();
        fill.setDirection(new Vector3f(1, 0, -1).normalizeLocal());
        fill.setColor(ColorRGBA.White.mult(1f));
        rootNode.addLight(fill);

        DirectionalLight fill2 = new DirectionalLight();
        fill2.setDirection(new Vector3f(-1, 0, 1).normalizeLocal());
        fill2.setColor(ColorRGBA.White.mult(1f));
        rootNode.addLight(fill2);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //orbitCamera.updateCamera();
    }

    @Override
    public void simpleRender(RenderManager rm) {
        // optional render code
    }

    public Earth getEarth() {
        return earth;
    }
}
