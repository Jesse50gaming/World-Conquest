package com.worldconquest.controls;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Set;

import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.worldconquest.City;
import com.worldconquest.WorldConquest;
import com.worldconquest.WorldConquest.GameState;
import com.worldconquest.gui.Gui;
import com.worldconquest.gui.Gui.ScreenState;

public class PlayerInput implements ActionListener, AnalogListener {

    private final InputManager input;
    private final WorldConquest wc;
    private Gui gui;
    private static final String LEFT_CLICK = "LEFT_CLICK";
    private static final String E_PRESSED = "E_PRESSED";
    private MouseStates mousestate = MouseStates.NORMAL;
    Deque<City> selectedCities = new ArrayDeque<>();
    private int selectedCitiesGoal;

    public PlayerInput(InputManager input, WorldConquest wc) {
        this.input = input;
        this.wc = wc;
        gui = wc.getGui();
        selectedCities = new ArrayDeque<>();
        registerInputs();
    }

    private void registerInputs() {
        input.addMapping(LEFT_CLICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        input.addMapping(E_PRESSED, new KeyTrigger(KeyInput.KEY_E));


        input.addListener(this, LEFT_CLICK, E_PRESSED);
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (!isPressed) return;

        if (name.equals(E_PRESSED)) {
            if (gui.getScreenState() == ScreenState.GAME) {
                gui.toggleBusinessPanel();
            }

        }

        if (name.equals(LEFT_CLICK)) {
            if (mousestate == MouseStates.CITYSELECTION) {
                City city = wc.getClosestCity();
                if (city != null) {
                    selectedCities.addFirst(city);
                    if (selectedCities.size() > selectedCitiesGoal) {
                        selectedCities.removeLast();
                    }
                }
                
            }
        }
    }
    
    public void update() {

    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        // Continuous movement if needed
    }

    public enum MouseStates {
        NORMAL, CITYSELECTION
    }

    public void startCitySelection(int cityNum) {
        selectedCities.clear();
        selectedCitiesGoal = cityNum;
        mousestate = MouseStates.CITYSELECTION;
    }

    public boolean selectedCitiesFull() {
        return selectedCities.size() == selectedCitiesGoal;
    }

    public void endCitySelection() {
        selectedCities.clear();
        selectedCitiesGoal = 0;
        mousestate = MouseStates.NORMAL;
    }
    

    
}
