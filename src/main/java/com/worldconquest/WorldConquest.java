package com.worldconquest;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import com.jme3.app.SimpleApplication;

import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.worldconquest.controls.OrbitCamera;
import com.jme3.bounding.BoundingBox;

public class WorldConquest extends SimpleApplication {

    private Spatial earth;
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
        setUpLight();
        setUpEarth();
        setUpCamera();
        inputManager.setCursorVisible(true);
    }

    private void setUpEarth() {
        earth = assetManager.loadModel("Models/Earth/Earth_1_12756.glb");

        
        BoundingBox bb = (BoundingBox) earth.getWorldBound();
        float maxExtent = Math.max(bb.getXExtent(), Math.max(bb.getYExtent(), bb.getZExtent()));
        float scaleFactor = 10f / maxExtent; // scale to ~10 units
        earth.scale(scaleFactor);

        earth.rotate(0, FastMath.DEG_TO_RAD * 90, 0);
        earth.setLocalTranslation(0f, 0f, 0f);
        rootNode.attachChild(earth);
    }

    private void setUpCamera() {
        orbitCamera = new OrbitCamera(cam, inputManager, earth);
        inputManager.setCursorVisible(true);
        orbitCamera.updateCamera();
    }

    private void setUpLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, 0, -1).normalizeLocal());
        sun.setColor(ColorRGBA.White.mult(1f));  
        rootNode.addLight(sun);

        DirectionalLight moon = new DirectionalLight();
        moon.setDirection(new Vector3f(1, 0, 1).normalizeLocal());
        moon.setColor(ColorRGBA.White.mult(0.5f)); 
        rootNode.addLight(moon);

        DirectionalLight fill = new DirectionalLight();
        fill.setDirection(new Vector3f(1, 0, -1).normalizeLocal());
        fill.setColor(ColorRGBA.White.mult(0.3f));
        rootNode.addLight(fill);

        DirectionalLight fill2 = new DirectionalLight();
        fill2.setDirection(new Vector3f(-1, 0, 1).normalizeLocal());
        fill2.setColor(ColorRGBA.White.mult(0.3f));
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
}
