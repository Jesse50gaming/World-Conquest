
package com.worldconquest.controls;

import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import com.worldconquest.WorldConquest;

/**
 * Orbit-style camera that keeps cursor visible and moves naturally.
 */
public class OrbitCamera implements AnalogListener, ActionListener {

    private final Camera cam;
    private final InputManager inputManager;
    private final Spatial target;

    private float distance;
    private float minDistance;
    private float maxDistance;

    private float azimuth = 0f; // horizontal angle
    private float elevation = FastMath.PI / 6; // vertical angle
    private float rotationSpeed = 1f;
    private float zoomSpeed;
    private float radius;
    private boolean rotating = false;
    private WorldConquest wc;
    

    public OrbitCamera(Camera cam, InputManager inputManager, Spatial target,WorldConquest wc) {
        this.cam = cam;
        this.inputManager = inputManager;
        this.target = target;
        registerInput();
        this.wc = wc;
        this.radius = wc.getEarth().getRadius();
        this.distance = radius * 1.5f;
        this.minDistance = radius * 1.1f;
        this.maxDistance = radius * 2.8f;
        this.zoomSpeed = radius * 0.08f;
    }

    private void registerInput() {
        inputManager.addMapping("OrbitRotate", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addMapping("OrbitUp", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping("OrbitDown", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("OrbitLeft", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("OrbitRight", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("ZoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping("ZoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));

        inputManager.addListener(this, "OrbitRotate", "OrbitUp", "OrbitDown", "OrbitLeft", "OrbitRight", "ZoomIn", "ZoomOut");
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (rotating) {
            if (name.equals("OrbitLeft")) azimuth += value * rotationSpeed;
            if (name.equals("OrbitRight")) azimuth -= value * rotationSpeed;
            if (name.equals("OrbitUp")) elevation += value * rotationSpeed * -1;
            if (name.equals("OrbitDown")) elevation -= value * rotationSpeed * -1;

            elevation = FastMath.clamp(elevation, -FastMath.HALF_PI, FastMath.HALF_PI);
        }

        if (name.equals("ZoomIn")) distance -= value * zoomSpeed;
        if (name.equals("ZoomOut")) distance += value * zoomSpeed;
        distance = FastMath.clamp(distance, minDistance, maxDistance);

        updateCamera();
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("OrbitRotate")) rotating = isPressed;
    }

    public void updateCamera() {
        if (target == null) return;
        inputManager.setCursorVisible(true);
        Vector3f targetPos = target.getWorldTranslation();
        float x = targetPos.x + distance * FastMath.cos(elevation) * FastMath.sin(azimuth);
        float y = targetPos.y + distance * FastMath.sin(elevation);
        float z = targetPos.z + distance * FastMath.cos(elevation) * FastMath.cos(azimuth);
        cam.setLocation(new Vector3f(x, y, z));
        cam.lookAt(targetPos, Vector3f.UNIT_Y);
    }
}
