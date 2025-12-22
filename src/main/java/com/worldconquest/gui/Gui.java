package com.worldconquest.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import org.w3c.dom.Text;

import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import com.worldconquest.City;
import com.worldconquest.WorldConquest;
import com.worldconquest.buildings.Building;
import com.worldconquest.departments.Department;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.StyleBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.scrollpanel.builder.ScrollPanelBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.controls.window.builder.WindowBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Gui implements ScreenController {
    private WorldConquest wc;
    private Nifty nifty;


    private static float heightScalar;
    private static float widthScalar;

    private static final String BUTTON_IMAGE = "Interface/Images/ButtonBackground.png";

    // ------------------ Font file constants ------------------
    private static final String ZEN_96 = "Interface/Fonts/96/ZenDots96.fnt";
    private static final String ZEN_72 = "Interface/Fonts/72/ZenDots72.fnt";
    private static final String ZEN_64 = "Interface/Fonts/64/ZenDots64.fnt";
    private static final String ZEN_48 = "Interface/Fonts/48/ZenDots48.fnt";
    private static final String ZEN_32 = "Interface/Fonts/32/ZenDots32.fnt";
    private static final String ZEN_24 = "Interface/Fonts/24/ZenDots24.fnt";
    private static final String ZEN_16 = "Interface/Fonts/16/ZenDots16.fnt";
    private static final String ZEN_12 = "Interface/Fonts/12/ZenDots12.fnt";

    // ------------------ Style id constants ------------------
    private static final String BUTTON_STYLE_16 = "button_style16";
    private static final String BUTTON_STYLE_32 = "button_style32";
    private static final String BUTTON_STYLE_64 = "button_style64";
    private static final String BUTTON_STYLE_96 = "button_style96";

    private static final String TEXTFIELD_STYLE_16 = "text_field_style16";
    private static final String TEXTFIELD_STYLE_32 = "text_field_style32";
    private static final String TEXTFIELD_STYLE_64 = "text_field_style64";
    private static final String TEXTFIELD_STYLE_96 = "text_field_style96";

    private static final String BUTTON_STYLE_12 = "button_style12";
    private static final String BUTTON_STYLE_24 = "button_style24";
    private static final String BUTTON_STYLE_48 = "button_style48";
    private static final String BUTTON_STYLE_72 = "button_style72";

    private static final String TEXTFIELD_STYLE_12 = "text_field_style12";
    private static final String TEXTFIELD_STYLE_24 = "text_field_style24";
    private static final String TEXTFIELD_STYLE_48 = "text_field_style48";
    private static final String TEXTFIELD_STYLE_72 = "text_field_style72";

    // ------------------ Screen / Layer / Panel / Control ID constants
    // ------------------
    public static final String SCREEN_START = "start";
    public static final String SCREEN_NEW_GAME = "new_game";
    public static final String SCREEN_GAME = "game";

    public static final String LAYER_MAIN = "layer";
    public static final String LAYER_HUD = "hud";
    

    // Start screen IDs
    public static final String PANEL_TITLE = "title_panel";
    public static final String TEXT_TITLE = "title";
    public static final String PANEL_LOAD_GAME_BUTTON = "load_game_button_panel";
    public static final String BUTTON_LOAD_GAME = "load_game_button";
    public static final String PANEL_NEW_GAME_BUTTON = "new_game_button_panel";
    public static final String BUTTON_NEW_GAME = "new_game_button";

    // New game screen IDs
    public static final String PANEL_NEW_GAME_TITLE = "new_game_title_panel";
    public static final String TEXT_NEW_GAME_TITLE = "new_game_title";
    public static final String PANEL_BACK_BUTTON = "back_button_panel";
    public static final String BUTTON_BACK = "back_button";
    public static final String PANEL_NAME_INPUT = "name_input_panel";
    public static final String TEXTFIELD_NAME_INPUT = "name_input";
    public static final String PANEL_DEPARTMENT_BUTTONS = "department_button_panel";
    public static final String BUTTON_BASIC_ORE = "basic_ore_mining_button";
    public static final String BUTTON_BUS_TRANSPORT = "bus_transportation_button";
    public static final String BUTTON_STEEL_MANUFACTURING = "steel_manufacturing_button";
    public static final String PANEL_START_GAME_BUTTON = "start_game_button_panel";
    public static final String BUTTON_START_GAME = "start_game_button";

    // Game screen / HUD IDs
    
    public static final String HUD_LAYER_ID = "HUD"; 
    public static final String PANEL_NAME = "name_panel";
    public static final String BUTTON_NAME = "name";
    public static final String TEXT_DATE = "date";
    public static final String TEXT_MONEY = "money";

//Business Panel
    public static final String LAYER_BUSINESS_PANEL = "BUSINESS_PANEL_LAYER";
    public static final String LEFT_PANEL = "LEFT_PANEL";
    public static final String RIGHT_PANEL = "RIGHT_PANEL";
    public static final String CENTER_PANEL = "CENTER_PANEL";
    public static final String BUSINESS_PANEL_IMAGE = "Interface/Images/BusinessPanel.png";
    public static final String BUSINESS_PANEL = "BUSINESS_PANEL";
    public static final String BUSINESS_NAME_TEXT = "BUSINESS_NAME";
    public static final String DEPARTMENT_SCROLL_PANEL = "DEPARTMENT_SCROLL_PANEL";
    public static final String DEPARTMENTS = "DEPARTMENTS";
    
    public static final String POP_UP_LAYER = "POP_UP_LAYER";

    //department window
    public static final String DEPARTMENT_PANEL_BUILD = "DEPARTMENT_PANEL_BUILD";
    
    //Business Departments
    public static HashMap<String, Department> departmentButtonIDs;
    public static HashMap<String, Department> departmentPanelIDs;
    public static HashMap<String, City> cityPanelIDs;

    public enum ScreenState {
        START_GAME,
        NEW_GAME,
        GAME
    }

    private HashMap<ScreenState, String> screenStateMap;
    
    //Business Panel
    private ArrayList<Department> departments;

    //Other
    private String chosenDepartment = "Not chosen";
    private ScreenState screenState = ScreenState.START_GAME;
    private int screenHeight;
    private int screenWidth;

    
    private HashMap<String, Supplier<String>> changingTextSupplier;
    private HashMap<String, String> changingTextScreen;

    private HashMap<String, String> startingDepartmentsButtons;

    public Gui(WorldConquest wc) {
        this.wc = wc;
        startingDepartmentsButtons = new HashMap<>();
        screenStateMap = new HashMap<>();
        
        departments = new ArrayList<>();
        changingTextScreen = new HashMap<>();
        changingTextSupplier = new HashMap<>();
        departmentButtonIDs = new HashMap<>();
        departmentPanelIDs = new HashMap<>();
        cityPanelIDs = new HashMap<>();
        AppSettings settings = wc.getContext().getSettings();
        screenHeight = settings.getHeight();
        screenWidth = settings.getWidth();

        heightScalar = screenHeight / 1440;
        widthScalar = screenWidth / 3440;
        initScreenStateMap();
    }

    private void initScreenStateMap() {
        screenStateMap.put(ScreenState.START_GAME, SCREEN_START);
        screenStateMap.put(ScreenState.NEW_GAME, SCREEN_NEW_GAME);
        screenStateMap.put(ScreenState.GAME, SCREEN_GAME);
    }

    private void createStyles() {
        if (nifty == null) return;
        createButtonStyle(BUTTON_STYLE_16, ZEN_16);
        createButtonStyle(BUTTON_STYLE_32, ZEN_32);
        createButtonStyle(BUTTON_STYLE_64, ZEN_64);
        createButtonStyle(BUTTON_STYLE_96, ZEN_96);

        createTextFieldStyle(TEXTFIELD_STYLE_16, ZEN_16);
        createTextFieldStyle(TEXTFIELD_STYLE_32, ZEN_32);
        createTextFieldStyle(TEXTFIELD_STYLE_64, ZEN_64);
        createTextFieldStyle(TEXTFIELD_STYLE_96, ZEN_96);

        createButtonStyle(BUTTON_STYLE_12, ZEN_12);
        createButtonStyle(BUTTON_STYLE_24, ZEN_24);
        createButtonStyle(BUTTON_STYLE_48, ZEN_48);
        createButtonStyle(BUTTON_STYLE_72, ZEN_72);

        createTextFieldStyle(TEXTFIELD_STYLE_12, ZEN_12);
        createTextFieldStyle(TEXTFIELD_STYLE_24, ZEN_24);
        createTextFieldStyle(TEXTFIELD_STYLE_48, ZEN_48);
        createTextFieldStyle(TEXTFIELD_STYLE_72, ZEN_72);
    }

    private void createTextFieldStyle(String id, String fontFile) {
        // Root
        new StyleBuilder() {
            {
                this.id(id);
                childLayoutOverlay();
                backgroundImage("Interface/Images/ButtonBackground.png");
                visibleToMouse();
            }
        }.build(nifty);

        // Field
        new StyleBuilder() {
            {
                id(id + "#field");
                childLayoutOverlay();
            }
        }.build(nifty);

        // Text
        new StyleBuilder() {
            {
                id(id + "#text");
                font(fontFile);
                color("#ffffffff");
            }
        }.build(nifty);

        // Cursor
        new StyleBuilder() {
            {
                id(id + "#cursor");
                backgroundColor("#ffffffff");
                width("2px");
            }
        }.build(nifty);

        // Cursor panel
        new StyleBuilder() {
            {
                id(id + "#cursor-panel");
                childLayoutCenter();
                backgroundColor("#00000000");
            }
        }.build(nifty);

        // Selection highlight
        new StyleBuilder() {
            {
                id(id + "#selection");
                backgroundColor("#272727ff");
            }
        }.build(nifty);
    }

    private void createButtonStyle(String id, String fontFile) {
        new StyleBuilder() {
            {
                this.id(id);
                childLayoutCenter();
                backgroundImage("Interface/Images/ButtonBackground.png");
                visibleToMouse();
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id(id + "#selected");
                backgroundImage("Interface/Images/ButtonBackgroundSelected.png");
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id(id + "#panel");
                childLayoutCenter();
                backgroundColor("#00000000");
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id(id + "#text");
                font(fontFile);
                color("#ffffffff");
                
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id(id + "#focus");
                backgroundImage("Interface/Images/ButtonBackgroundSelected.png");
            }
        }.build(nifty);

        new StyleBuilder() {
            {
                id(id + "#disabled");
                color("#000000ff");
            }
        }.build(nifty);
    }

    private void loadFonts() {
        

        nifty.getRenderEngine().createFont(ZEN_16);
        nifty.getRenderEngine().createFont(ZEN_32);
        nifty.getRenderEngine().createFont(ZEN_64);
        nifty.getRenderEngine().createFont(ZEN_96);
        nifty.getRenderEngine().createFont(ZEN_12);
        nifty.getRenderEngine().createFont(ZEN_24);
        nifty.getRenderEngine().createFont(ZEN_48);
        nifty.getRenderEngine().createFont(ZEN_72);
    }

    private String scaleFont(String font1440, String font1080) {
        if (screenHeight == 1440) {
            return font1440;
        } else if (screenHeight == 1080) {
            return font1080;
        } else {
            return font1440;
        }
    }

    public ScreenState getScreenState() {
        return screenState;
    }


    public void initGui() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(wc.getAssetManager(), wc.getInputManager(), wc.getAudioRenderer(), wc.getGuiViewPort());
        wc.getGuiViewPort().addProcessor(niftyDisplay);
        nifty = niftyDisplay.getNifty();
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        
        
        //nifty.setDebugOptionPanelColors(true); 
                                                
        loadFonts();
        createStyles();

        // ---------------- Start Screen ----------------
        nifty.addScreen(SCREEN_START, new ScreenBuilder(SCREEN_START) {
            {
                controller(Gui.this);

                layer(new LayerBuilder(LAYER_MAIN) {
                    {
                        childLayoutVertical();

                        panel(new PanelBuilder() {
                            {
                                height("5%");
                            }
                        });

                        // Title Panel
                        panel(new PanelBuilder(PANEL_TITLE) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder(TEXT_TITLE) {
                                    {
                                        text("World Conquest");
                                        font(scaleFont(ZEN_96, ZEN_72));
                                        alignCenter();
                                        height("100%");
                                        width("100%");
                                    }
                                });
                            }
                        });

                        // Spacer
                        panel(new PanelBuilder() {
                            {
                                width("20%");
                                height("5%");
                            }
                        });

                        // Load Game Button Panel
                        panel(new PanelBuilder(PANEL_LOAD_GAME_BUTTON) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();
                                backgroundColor("#3D3232FF");

                                control(new ButtonBuilder(BUTTON_LOAD_GAME, "Load Game") {
                                    {
                                        style(scaleFont(BUTTON_STYLE_64, BUTTON_STYLE_48));
                                        height("100%");
                                        width("100%");
                                        valignCenter();
                                        alignCenter();
                                        interactOnClick("startGame()");
                                    }
                                });
                            }
                        });

                        // Spacer
                        panel(new PanelBuilder() {
                            {
                                width("20%");
                                height("5%");
                            }
                        });

                        // New Game Button Panel
                        panel(new PanelBuilder(PANEL_NEW_GAME_BUTTON) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new ButtonBuilder(BUTTON_NEW_GAME, "New Game") {
                                    {
                                        height("100%");
                                        width("100%");
                                        style(scaleFont(BUTTON_STYLE_64, BUTTON_STYLE_48));
                                        alignCenter();
                                        interactOnClick("newGameScreen()");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.build(nifty));

        // ---------------- New Game Screen ----------------
        nifty.addScreen(SCREEN_NEW_GAME, new ScreenBuilder(SCREEN_NEW_GAME) {
            {
                controller(Gui.this);

                layer(new LayerBuilder() {
                    {
                        childLayoutVertical();

                        // new game title
                        panel(new PanelBuilder(PANEL_NEW_GAME_TITLE) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder(TEXT_NEW_GAME_TITLE) {
                                    {
                                        text("New Game");
                                        font(scaleFont(ZEN_96, ZEN_72));
                                        height("100%");
                                        width("100%");
                                        alignCenter();
                                    }
                                });
                            }
                        });

                        panel(new PanelBuilder() {
                            {
                                height("5%");
                            }
                        });

                        // back
                        panel(new PanelBuilder(PANEL_BACK_BUTTON) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new ButtonBuilder(BUTTON_BACK, "Back") {
                                    {
                                        height("100%");
                                        width("100%");
                                        style(scaleFont(BUTTON_STYLE_64, BUTTON_STYLE_48));
                                        alignCenter();
                                        interactOnClick("backToStart()");
                                    }
                                });
                            }
                        });

                        panel(new PanelBuilder() {
                            {
                                height("5%");
                            }
                        });

                        // name input
                        panel(new PanelBuilder(PANEL_NAME_INPUT) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new TextFieldBuilder(TEXTFIELD_NAME_INPUT) {
                                    {
                                        height("100%");
                                        width("100%");
                                        style(scaleFont(TEXTFIELD_STYLE_64, TEXTFIELD_STYLE_48));
                                        alignCenter();
                                    }
                                });
                            }
                        });

                        panel(new PanelBuilder() {
                            {
                                height("5%");
                            }
                        });

                        // department button
                        panel(new PanelBuilder(PANEL_DEPARTMENT_BUTTONS) {
                            {
                                width("40%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutHorizontal();

                                control(new ButtonBuilder(BUTTON_BASIC_ORE, "Basic Ore \n Mining") {
                                    {
                                        style(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24));
                                        height("100%");
                                        width("30%");
                                        alignCenter();
                                        startingDepartmentsButtons.put("Basic Ore Mining", BUTTON_BASIC_ORE);
                                        interactOnClick("chooseDepartment(Basic Ore Mining)");
                                    }
                                });

                                panel(new PanelBuilder() {
                                    {
                                        width("5%");
                                    }
                                });

                                control(new ButtonBuilder(BUTTON_BUS_TRANSPORT, "Bus \n Transportation") {
                                    {
                                        height("100%");
                                        width("30%");
                                        startingDepartmentsButtons.put("Bus Transportation", BUTTON_BUS_TRANSPORT);
                                        style(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24));
                                        alignCenter();
                                        interactOnClick("chooseDepartment(Bus Transportation)"); 
                                    }
                                });

                                panel(new PanelBuilder() {
                                    {
                                        width("5%");
                                    }
                                });

                                control(new ButtonBuilder(BUTTON_STEEL_MANUFACTURING, "Steel \n Manufacturing") {
                                    {
                                        height("100%");
                                        width("30%");
                                        startingDepartmentsButtons.put("Steel Manufacturing", BUTTON_STEEL_MANUFACTURING);
                                        style(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24));
                                        alignCenter();
                                        interactOnClick("chooseDepartment(Steel Manufacturing)");
                                        
                                    }
                                });
                            }
                        });

                        panel(new PanelBuilder() {
                            {
                                height("5%");
                            }
                        });

                        // start game
                        panel(new PanelBuilder(PANEL_START_GAME_BUTTON) {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new ButtonBuilder(BUTTON_START_GAME, "Start New Game") {
                                    {
                                        style(scaleFont(BUTTON_STYLE_64, BUTTON_STYLE_32));
                                        height("100%");
                                        width("100%");
                                        alignCenter();
                                        interactOnClick("startNewGame()");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }.build(nifty));

        // ---------------- Game Screen ----------------
        nifty.addScreen(SCREEN_GAME, new ScreenBuilder(SCREEN_GAME) {
            {
                controller(Gui.this);

                
                
                layer(new LayerBuilder(LAYER_BUSINESS_PANEL) {
                    {
                        childLayoutVertical();
                        visible(false);
                        valignCenter();
                        alignCenter();
                        panel(new PanelBuilder() {
                            {
                                height("20%");
                            }
                        });

                        panel(new PanelBuilder(BUSINESS_PANEL) {
                            {
                                backgroundImage(BUSINESS_PANEL_IMAGE);
                                height("60%");
                                width("50%");
                                alignCenter();
                                valignCenter();
                                childLayoutHorizontal();
                                

                                
                                panel(new PanelBuilder(LEFT_PANEL) {
                                    {
                                        height("100%");
                                        width("33%");
                                        childLayoutVertical();
                                        valignCenter();
                                        
                                    }
                                });
                                panel(new PanelBuilder(CENTER_PANEL) {
                                    {
                                        height("100%");
                                        width("33%");
                                        childLayoutVertical();
                                        valignCenter();

                                        panel(new PanelBuilder() {
                                            {
                                                height("5%");
                                            }
                                        });

                                        text(new TextBuilder(BUSINESS_NAME_TEXT){
                                            {
                                                text("not Selected");
                                                changingText(BUSINESS_NAME_TEXT, SCREEN_GAME, () -> wc.getPlayer().getName());
                                                font(scaleFont(ZEN_32,ZEN_24));
                                                width("100%");
                                                height("10%");
                                                alignCenter();
                                            }
                                        });


                                    }
                                });
                                panel(new PanelBuilder(RIGHT_PANEL) {
                                    {
                                        height("100%");
                                        width("33%");
                                        childLayoutVertical();
                                        valignCenter();
                                        panel(new PanelBuilder() {
                                            {
                                                childLayoutVertical();
                                                height("5%");
                                                text(new TextBuilder() {
                                                    {
                                                        text("Departments");
                                                        font(scaleFont(ZEN_32, ZEN_24));
                                                        height("100%");
                                                        width("100%");
                                                        alignCenter();
                                                    }
                                                });
                                            }
                                        }); 

                                        
                                        control(new ScrollPanelBuilder(DEPARTMENT_SCROLL_PANEL) {
                                            {
                                                width("100%");
                                                height("50%");
                                                childLayoutVertical();

                                                panel(new PanelBuilder(DEPARTMENTS) {
                                                    {
                                                        height("100%");
                                                        width("100%");
                                                        childLayoutVertical();
                                                        backgroundImage(BUSINESS_PANEL_IMAGE);
                                                    }
                                                });
                                            
                                            }
                                        });

                                    }
                                });

                            }
                        });

                        
                    }
                });

                

                layer(new LayerBuilder(LAYER_HUD) {
                    {
                        childLayoutVertical();

                        panel(new PanelBuilder() {
                            {
                                height("95%");
                            }
                        });

                        panel(new PanelBuilder(HUD_LAYER_ID) {
                            {
                                width("100%");
                                height("5%");
                                childLayoutHorizontal();
                                backgroundImage("Interface/Images/HUD.png");
                                valign(VAlign.Bottom);
                                alignCenter();

                                panel(new PanelBuilder(PANEL_NAME) {
                                    {
                                        width("10%");
                                        height("100%");
                                        childLayoutVertical();
                                        valign(VAlign.Bottom);
                                        alignCenter();

                                        control(new ButtonBuilder(BUTTON_NAME, "not Chosen") {
                                            {
                                                text("not set");
                                                style(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24)); 
                                                alignCenter();
                                                changingText(BUTTON_NAME, SCREEN_GAME, () -> wc.getPlayer().getName());
                                                valign(VAlign.Center);
                                                width("80%");
                                                height("50%");
                                                interactOnClick("toggleBusinessPanel()");
                                            }
                                        });

                                        text(new TextBuilder(TEXT_DATE) {
                                            {
                                                text("not set");
                                                font(scaleFont(ZEN_32, ZEN_16));
                                                changingText(TEXT_DATE, SCREEN_GAME, () -> wc.getDate());
                                                alignLeft();
                                                width("100%");
                                                height("50%");
                                            }
                                        });
                                    }
                                });

                                text(new TextBuilder(TEXT_MONEY) {
                                    {
                                        text("not set");
                                        font(scaleFont(ZEN_32, ZEN_24));
                                        changingText(TEXT_MONEY, SCREEN_GAME, () -> wc.getPlayer().getMoneyString());
                                        alignLeft();
                                        valign(VAlign.Center);
                                        width("5%");
                                        height("100%");
                                    }
                                });
                            }
                        });
                    }
                });

                layer(new LayerBuilder(POP_UP_LAYER) {
                    {
                        childLayoutAbsolute();
                        visible(true);

                    }
                });
            }
        }.build(nifty));

        setScreenState(ScreenState.START_GAME);
    }

    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
    }

    @Override
    public void onStartScreen() {
        // no-op
    }

    @Override
    public void onEndScreen() {
        // no-op
    }

    public void update() {
        updateChangingText();
        
    }

    

    private String getDate() {
        return wc.getDate();
    }

    private String getNameString() {
        return wc.getPlayer().getName();
    }

    public void setScreenState(ScreenState state) {
        screenState = state;
        nifty.gotoScreen(screenStateMap.get(state));
    }

    public void startNewGame() {
        wc.startNewGame();
        
        
    }

    public void newGameScreen() {
        setScreenState(ScreenState.NEW_GAME);
    }

    public void backToStart() {
        setScreenState(ScreenState.START_GAME);
    }

    public Nifty getNifty() {
        return nifty;
    }

    

    public void updateDepartments(Department department) {
        if (departments.contains(department)) return;
        departments.add(department);

        Screen gameScreen = nifty.getScreen(SCREEN_GAME);
        Element departmentPanel = gameScreen.findElementById(DEPARTMENTS);
        Element popupLayer = gameScreen.findElementById(POP_UP_LAYER);

        if (departmentPanel == null || popupLayer == null) {
            System.out.println("Required UI elements not found!");
            return;
        }

        String panelId = department.getName() + "_Panel";
        String buttonId = department.getName() + "_Button";

        departmentPanelIDs.put(panelId, department);

        Element window;
        

        window = new WindowBuilder(panelId, department.getName()) {
            {
                x("35%");
                y("30%");
                width("30%");
                height("40%");
                visible(false);
                backgroundImage(BUSINESS_PANEL_IMAGE);

                childLayoutVertical();
                closeable(false);
                
                panel(new PanelBuilder("header") {
                    {
                        height("3%");
                        childLayoutAbsolute();
                        padding("0px");
                        margin("0px");
                        valignTop();

                        // Close button
                        control(new ButtonBuilder("closeBtn", "X") {
                            {
                                padding("0px");   
                                margin("0px");
                                x("95%");
                                y("-165%");
                                width("5%");
                                height("100%");
                                style(scaleFont(BUTTON_STYLE_16, BUTTON_STYLE_12));
                                interactOnClick("openDepartmentPanel(" + panelId + ")");//toggles visibility
                            }
                        });
                    }
                });

                // Content
                panel(new PanelBuilder(panelId + "_content") {
                    {
                        backgroundImage(BUSINESS_PANEL_IMAGE);
                        width("100%");
                        height("97%");
                        childLayoutHorizontal();

                        panel(new PanelBuilder(panelId + "_LEFT") {
                            {
                                width("50%");
                                height("100%");
                                childLayoutVertical();
                            }
                        });
                        
                        panel(new PanelBuilder(panelId + "_RIGHT") {
                            {
                                width("50%");
                                height("100%");
                                childLayoutVertical();

                                text(new TextBuilder(panelId + "_ACTIONS_TITLE") {
                                    {
                                        alignCenter();
                                        height("5%");
                                        text("Actions");
                                        font(scaleFont(ZEN_32, ZEN_24));
                                    }
                                });
                                control(new ScrollPanelBuilder(panelId+ "_ACTIONS_SCROLL_PANEL") {
                                    {
                                        alignCenter();
                                        backgroundColor("rgba(60, 55, 55, 1)");
                                        childLayoutVertical();
                                        height("95%");
                                        width("90%");

                                        panel(new PanelBuilder(panelId + "_ACTIONS") {
                                            {
                                                height("100%");
                                                width("100%");
                                                childLayoutVertical();
                                                alignCenter();
                                                backgroundImage(BUSINESS_PANEL_IMAGE);

                                                control(new ButtonBuilder(panelId + DEPARTMENT_PANEL_BUILD,"Build New") {
                                                    {
                                                        height("20%"); 
                                                        width("100%");
                                                        alignCenter();
                                                        style(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24));
                                                        interactOnClick("buildNew("+ panelId +")");
                                                        
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });

                            }
                        });

                        

                    }
                });

            }
        }.build(nifty, gameScreen, popupLayer);

        department.setWindow(window);

        // button
        new ButtonBuilder(buttonId, department.getName()) {
            {
                height("25%");
                width("100%");
                style(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24));
                alignCenter();
                interactOnClick("openDepartmentPanel(" + panelId + ")");
            }
        }.build(nifty, gameScreen, departmentPanel);
    }

    
    public void openDepartmentPanel(String departmentPanelID) {
        if (screenState != ScreenState.GAME || departmentPanelID == null) return;

        Department department = departmentPanelIDs.get(departmentPanelID);
        if (department == null) return;
        department.toggleWindow();
    }

    
    


    public void chooseDepartment(String departmentName) {
        chosenDepartment = departmentName;
        Screen screen = nifty.getScreen(SCREEN_NEW_GAME);

        for (String dept : startingDepartmentsButtons.keySet()) {
            String id = startingDepartmentsButtons.get(dept);
            Element button = screen.findElementById(id);

            if (button == null) continue;

            if (dept.equals(departmentName)) {
                button.setStyle(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24) + "#selected");
            } else {
                button.setStyle(scaleFont(BUTTON_STYLE_32, BUTTON_STYLE_24));
            }
        }
    }

    public void toggleBusinessPanel() {
        Screen screen = nifty.getScreen(SCREEN_GAME);
        Element bPanel = screen.findElementById(LAYER_BUSINESS_PANEL);

        if (bPanel != null) {
            bPanel.setVisible(!bPanel.isVisible());
        }
    }
    
    
    
    private void changingText(String id, String screen, Supplier<String> textSupplier) {
        changingTextScreen.put(id, screen);
        changingTextSupplier.put(id, textSupplier);
    }

    
    
    private void updateChangingText() {
        for (String id : changingTextScreen.keySet()) {
            Screen screen = nifty.getScreen(changingTextScreen.get(id));

            // text
            Element element = screen.findElementById(id);
            if (element != null) {
                TextRenderer tr = element.getRenderer(TextRenderer.class);
                if (tr != null) {
                    tr.setText(changingTextSupplier.get(id).get());
                    continue;
                }
            }

            // button
            Element buttonText = screen.findElementById(id + "#text");
            if (buttonText != null) {
                buttonText.getRenderer(TextRenderer.class).setText(changingTextSupplier.get(id).get());
                continue;
            }

            System.out.println(id + " has no text renderer");
        }
    }

    public void newBuildWindow(Department department) {
        Screen gameScreen = nifty.getScreen(SCREEN_GAME);
        if (gameScreen == null) return;
        Element popupLayer = gameScreen.findElementById(POP_UP_LAYER);
        if (popupLayer == null) return;

        String panelId = department.getName() + "_Window";
        String departmentPanelID = department.getName() + "_Panel";

        Element window = new WindowBuilder(panelId, "Build") {
            {
                x("10%");
                y("25%");
                width("15%");
                height("20%");
                visible(true);

                childLayoutVertical();
                closeable(false);

                // Content 
                panel(new PanelBuilder(panelId + "_content") {
                    {
                        width("100%");
                        height("100%");
                        childLayoutVertical();
                        backgroundImage(BUSINESS_PANEL_IMAGE);

                        panel(new PanelBuilder() {
                            {
                                height("20%");
                            }
                        });

                        panel(new PanelBuilder(panelId + "_Buttons") {
                            {
                                height("50%");
                                width("100%");
                                childLayoutHorizontal();
                                alignCenter();

                                control(new ButtonBuilder(panelId + "_buildBtn", "Build") {
                                    {
                                        height("100%");
                                        width("33%");
                                        valignCenter();
                                        style(scaleFont(BUTTON_STYLE_16, BUTTON_STYLE_12));
                                        alignCenter();
                                        interactOnClick("endBuild(" + departmentPanelID + ")");
                                    }
                                });

                                control(new ButtonBuilder(panelId + "_cancelBtn", "Cancel") {
                                    {
                                        height("100%");
                                        width("33%");
                                        valignCenter();
                                        style(scaleFont(BUTTON_STYLE_16, BUTTON_STYLE_12));
                                        alignCenter();
                                        interactOnClick("cancelBuild(" + panelId + ")");
                                    }
                                });
                                control(new ButtonBuilder(panelId + "_clearBtn", "Clear") {
                                    {
                                        height("100%");
                                        width("33%");
                                        valignCenter();
                                        style(scaleFont(BUTTON_STYLE_16, BUTTON_STYLE_12));
                                        alignCenter();
                                        interactOnClick("clearSelection()");
                                    }
                                });
                            }
                        });

                        text(new TextBuilder(panelId + "_ErrorText") {
                            {
                                text("");
                                font(scaleFont(ZEN_24, ZEN_16));
                                height("30%");
                                width("100%");
                                alignCenter();
                            }
                        });

                    }
                });

            }
        }.build(nifty, gameScreen, popupLayer);

    }

    public void newCityPanel(City city) {
        Screen gameScreen = nifty.getScreen(SCREEN_GAME);
        if (gameScreen == null) return;
        Element popupLayer = gameScreen.findElementById(POP_UP_LAYER);
        if (popupLayer == null) return;

        String panelId = city.getName() + "_Panel";

        if (gameScreen.findElementById(panelId) != null) return;
        cityPanelIDs.put(panelId, city);

        Element window = new WindowBuilder(panelId, city.getName() + ", " + city.getCountry().getName()) {
            {
                x("10%");
                y("25%");
                width("30%");
                height("40%");
                visible(true);

                childLayoutVertical();
                closeable(true);

                // Content 
                panel(new PanelBuilder(panelId + "_content") {
                    {
                        width("100%");
                        height("100%");
                        childLayoutHorizontal();
                        backgroundImage(BUSINESS_PANEL_IMAGE);

                        panel(new PanelBuilder(panelId + "_LEFT") {
                            {
                                width("50%");
                                height("100%");
                                childLayoutVertical();

                            }

                        });

                        panel(new PanelBuilder(panelId + "_RIGHT") {
                            {
                                width("50%");
                                height("100%");
                                childLayoutVertical();

                                text(new TextBuilder(panelId + "_BuildingTitle") {
                                    {
                                        text("Buildings");
                                        font(scaleFont(ZEN_16, ZEN_12));
                                        height("5%");
                                        width("100%");
                                        alignCenter();
                                    }
                                });

                                control(new ScrollPanelBuilder(panelId + "_BuildingScrollPanel") {
                                    {
                                        width("100%");
                                        height("95%");
                                        childLayoutVertical();

                                        panel(new PanelBuilder(panelId + "_Buildings") {
                                            {
                                                height("100%");
                                                width("100%");
                                                childLayoutVertical();
                                                alignCenter();
                                                backgroundImage(BUSINESS_PANEL_IMAGE);

                                                for (Building building : city.getBuildings()) {
                                                    panel(new PanelBuilder() {
                                                        {
                                                            width("100%");
                                                            height("20%");
                                                            backgroundImage(BUSINESS_PANEL_IMAGE);
                                                            childLayoutHorizontal();

                                                            text(new TextBuilder() {
                                                                {
                                                                    text(building.getName());
                                                                    font(scaleFont(ZEN_16, ZEN_12));
                                                                    height("100%");
                                                                    width("50%");
                                                                    valignCenter();
                                                                }
                                                            });

                                                            text(new TextBuilder(panelId + building.getName() + "_NumberOfBuildings") {
                                                                {
                                                                    text("number");
                                                                    changingText(panelId + building.getName() + "_NumberOfBuildings", SCREEN_GAME, () -> city.getNumberOfBuildings(building.getName()));
                                                                    font(scaleFont(ZEN_16, ZEN_12));
                                                                    height("100%");
                                                                    width("10%");
                                                                    valignCenter();
                                                                }
                                                            });

                                                            control(new ButtonBuilder(panelId + "_SellButton", "Sell 1") {
                                                                {
                                                                    height("100%");
                                                                    width("30%");
                                                                    style(scaleFont(BUTTON_STYLE_16, BUTTON_STYLE_12));
                                                                    alignCenter();
                                                                    interactOnClick("sellBuilding(" + panelId + "," + building.getName() + ")");
                                                                }
                                                            });

                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });

                    }
                });

            }
        }.build(nifty, gameScreen, popupLayer);

    }
    
    public void sellBuilding(String panelId, String buildingName) {
        City city = cityPanelIDs.get(panelId);
        if (city == null) {
            System.out.println("sellBuilding called with invalid panelId: " + panelId);
            return;
        }
        Building building = city.getBuilding(buildingName);

        city.removeBuilding(building, building.getDepartment());
    }
    
    public void cancelBuild(String windowPanelID) {
        removeElement(windowPanelID);
        wc.getPlayerInput().endCitySelection();
    }

    public void clearSelection() {
        wc.getPlayerInput().clearSelection();
    }
    
    public void endBuild(String departmentPanelID) {

        Department department = departmentPanelIDs.get(departmentPanelID);
        if (department == null) {
            System.out.println("endBuild called with invalid departmentPanelID: " + departmentPanelID);

            for (String id : departmentPanelIDs.keySet()) {
                System.out.println("Valid departmentPanelID: " + id);
            }
            return;
        }

        if (!wc.getPlayerInput().selectedCitiesNumValid()) {
            updateText(department.getName() + "_Window_ErrorText", "Not enough cities selected");
            return;
        }

        for (City city : wc.getPlayerInput().getSelectedCities()) {
            if (!wc.getPlayer().spendMoney(department.getBuildingCost())) {
                updateText(department.getName() + "_Window_ErrorText", "Not enough money");
                return;
            }
        }
        
        department.endBuild();
        removeElement(department.getName() + "_Window");
    }
    
    public void removeElement(String id) {
        Screen screen = nifty.getCurrentScreen();
        if(screen == null) return;
        Element element = screen.findElementById(id);
        if (element != null) {
            element.markForRemoval();
        }

    }


    public void updateText(String id, String text) {
        Screen screen = nifty.getCurrentScreen();
        if(screen == null) return;
        Element element = screen.findElementById(id);
        if (element != null) {
            TextRenderer tr = element.getRenderer(TextRenderer.class);
            if (tr != null) {
                tr.setText(text);
            }
        }
    }

    public void buildNew(String departmentPanelID) {
        
        departmentPanelIDs.get(departmentPanelID).startBuild();
    }

    public String getChosenDepartment() {
        return chosenDepartment;
    }

}
