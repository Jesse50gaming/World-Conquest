package com.worldconquest;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;

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

import de.lessvoid.nifty.controls.TextField;

public class WorldConquest extends SimpleApplication {

    public static int FPS = 60;
    public Earth earth;
    private OrbitCamera orbitCamera;
    
    

    private Gui gui;
    public GameState gameState = GameState.TITLE;
    private Calender calender;
    private Player player;

   

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

    private class Calender {
        int frameNum;
        int day, month, year;
        String date = "";
        int dayLegnth = 5;// in seconds
        WorldConquest wc;
        HashMap<Integer, Integer> monthDayHashMap = new HashMap<>();
        HashMap<Integer, String> monthNameHashMap = new HashMap<>();

        public Calender(int month, int day, int year, WorldConquest wc) {
            this.month = month;
            this.day = day;
            this.year = year;
            this.wc = wc;
            initCalender();
        }

        private void initCalender() {
            monthDayHashMap.put(1, 31); 
            monthDayHashMap.put(2, 28); 
            monthDayHashMap.put(3, 31); 
            monthDayHashMap.put(4, 30); 
            monthDayHashMap.put(5, 31); 
            monthDayHashMap.put(6, 30); 
            monthDayHashMap.put(7, 31); 
            monthDayHashMap.put(8, 31); 
            monthDayHashMap.put(9, 30); 
            monthDayHashMap.put(10, 31);
            monthDayHashMap.put(11, 30);
            monthDayHashMap.put(12, 31);

            monthNameHashMap.put(1, "January");
            monthNameHashMap.put(2, "February");
            monthNameHashMap.put(3, "March");
            monthNameHashMap.put(4, "April");
            monthNameHashMap.put(5, "May");
            monthNameHashMap.put(6, "June");
            monthNameHashMap.put(7, "July");
            monthNameHashMap.put(8, "August");
            monthNameHashMap.put(9, "September");
            monthNameHashMap.put(10, "October");
            monthNameHashMap.put(11, "November");
            monthNameHashMap.put(12, "December");
            

        }

        public void update() {
            if (frameNum == FPS * dayLegnth) {
                frameNum = 0;
                dailyUpdate();
            }
            frameNum++;
        }

        private void dailyUpdate() {
            wc.dailyUpdate();
            day++;
            if (day > monthDayHashMap.get(month)) {
                day = 1;
                month++;
            }
            if (month > 12) {
                month = 1;
                year++;
            }

        }

        public String getDate() {
            date = monthNameHashMap.get(month) + " " + day + ", " + year;
            
            return date;
        }

    }
    

    private enum GameState {
        TITLE,
        GAME,
    }

    @Override
    public void simpleInitApp() {  
        earth = new Earth(this);
        earth.loadCitiesFromGeoNames(50000);
        initLight();
        initCamera();
        gui = new Gui(this);
        gui.initGui(); 
        
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

    public void startNewGame() {

        TextField nameField = gui.getNifty().getCurrentScreen().findNiftyControl("name_input", TextField.class);
        String businessName = nameField.getRealText();
        if (businessName.isEmpty()) {
            gui.newGameScreen();
            return;
        }
        player = new Player(businessName, this);
        calender = new Calender(1, 1, 2025, this);
        gameState = GameState.GAME;
    }

    
    
    private void cityRayCast() {
        if (gameState != GameState.GAME) return;
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

        if (gameState == GameState.GAME) {
            cityRayCast();
            earth.update();
            gui.update();
            calender.update();
        }
        
        
    }
    
    public void dailyUpdate() {
        earth.dailyUpdate();
    }

    @Override
    public void simpleRender(RenderManager rm) {
        // optional render code
    }

    public Earth getEarth() {
        return earth;
    }

    public Gui getGui() {
        return gui;
    }

    public Player getPlayer() {
        return player;
    }

    public String getDate() {
        return calender.getDate();
    }


}
