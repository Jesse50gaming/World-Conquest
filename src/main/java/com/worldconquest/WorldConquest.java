package com.worldconquest;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.bounding.BoundingBox;

public class WorldConquest extends SimpleApplication {

    private Spatial earth;

    public static void main(String[] args) {
        WorldConquest app = new WorldConquest();

        // Create AppSettings and enable fullscreen
        AppSettings settings = new AppSettings(true);
        settings.setFullscreen(true);                // enable fullscreen
        settings.setResolution(1920, 1080);          // set resolution (optional)
        settings.setTitle("World Conquest");         // window title
        app.setSettings(settings);
        app.setShowSettings(false); // skip settings dialog
        
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
        ChaseCamera chaseCam = new ChaseCamera(cam, earth, inputManager);

    
        chaseCam.setDragToRotate(true);

       
        chaseCam.setHideCursorOnRotate(false);

        
        chaseCam.setDefaultDistance(20f);
        chaseCam.setMinDistance(11f);
        chaseCam.setMaxDistance(100f);
        chaseCam.setRotationSpeed(2f);
        chaseCam.setSmoothMotion(false);
        chaseCam.setZoomSensitivity(2f);
        chaseCam.setDefaultVerticalRotation(FastMath.PI / 6);
        chaseCam.setInvertVerticalAxis(true);

        
        enqueue(() -> {
            inputManager.setCursorVisible(true);
            return null;
        });
    }

    private void setUpLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, 0, -1).normalizeLocal());
        sun.setColor(ColorRGBA.White.mult(1f));  
        rootNode.addLight(sun);

        DirectionalLight moon = new DirectionalLight();
        moon.setDirection(new Vector3f(1, 0, 1).normalizeLocal());
        moon.setColor(ColorRGBA.White.mult(0.4f)); 
        rootNode.addLight(moon);

        DirectionalLight fill = new DirectionalLight();
        fill.setDirection(new Vector3f(1, 0, -1).normalizeLocal());
        fill.setColor(ColorRGBA.White.mult(0.2f));
        rootNode.addLight(fill);

        DirectionalLight fill2 = new DirectionalLight();
        fill2.setDirection(new Vector3f(-1, 0, 1).normalizeLocal());
        fill2.setColor(ColorRGBA.White.mult(0.2f));
        rootNode.addLight(fill2);
    }

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        // optional render code
    }
}
