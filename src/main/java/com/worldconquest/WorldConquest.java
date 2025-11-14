package com.worldconquest;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;


import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;
import com.worldconquest.controls.OrbitCamera;

public class WorldConquest extends SimpleApplication {

    public static int FPS = 60;
    public Earth earth;
    private OrbitCamera orbitCamera;
    private int frameNum = 0;
    private int slowUpdateSpeed = 5; //seconds

    public static void main(String[] args) {
        WorldConquest app = new WorldConquest();

        AppSettings settings = new AppSettings(true);
        settings.setFullscreen(true);
        
        
        DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        settings.setResolution(mode.getWidth(), mode.getHeight());

        settings.setResizable(false);
        settings.setTitle("World Conquest");
        settings.setFrameRate(FPS);
        app.setDisplayStatView(false);
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
        
    }

    @Override
    public void simpleInitApp() {  
        earth = new Earth(this);
        earth.loadCitiesFromGeoNames(50000);
        initLight();
        initCamera();

        
    }
    
    

    private void initCamera() {
        orbitCamera = new OrbitCamera(cam, inputManager, earth.getEarthSpatial(),this);
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
    
    private void cityRayCast() {
        Vector2f mousePos = inputManager.getCursorPosition();
        CollisionResults results = new CollisionResults();
        Vector3f click3d = cam.getWorldCoordinates(mousePos, 0f).clone();
        Vector3f dir = cam.getWorldCoordinates(mousePos, 1f).subtractLocal(click3d).normalizeLocal();
        Ray ray = new Ray(click3d, dir);
        Vector2f cursor = inputManager.getCursorPosition();
        rootNode.collideWith(ray, results);

        if (results.size() > 0) {
            Geometry target = results.getClosestCollision().getGeometry();

            if (target.getUserData("cityName") != null) {
                String name = target.getUserData("cityName");
                int pop = target.getUserData("population");
                String countryName = target.getUserData("countryName");
                guiNode.detachAllChildren();

                BitmapText info = new BitmapText(guiFont);
                info.setSize(guiFont.getCharSet().getRenderedSize());
                info.setColor(ColorRGBA.White);
                info.setText(name + ", " + countryName + "\nPop: " + formatPopulation(pop));
                info.setLocalTranslation(cursor.x + 15, cursor.y + 15, 0);
                guiNode.attachChild(info);
            } else {
                guiNode.detachAllChildren();
            }
        } else {
            guiNode.detachAllChildren();
        }
    }

    private String formatPopulation(int population) {
        if (population >= 1000000) {
            return String.format("%.1fm", population / 1000000.0);
        } else if (population >= 1000) {
            return String.format("%.0fk", population / 1000.0);
        } else {
            return String.valueOf(population);
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        cityRayCast();

        if (frameNum == FPS * slowUpdateSpeed) {
            frameNum = 0;
            slowUpdate();
        }
        frameNum++;
    }
    
    private void slowUpdate() {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        // optional render code
    }

    public Earth getEarth() {
        return earth;
    }
}
