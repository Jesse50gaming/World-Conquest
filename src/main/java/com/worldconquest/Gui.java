package com.worldconquest;

import java.util.HashMap;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.StyleBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Gui implements ScreenController {
    private WorldConquest wc;
    private Nifty nifty;

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
    public static final String LAYER_BUSINESS_PANEL = "business panel";

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
    public static final String PANEL_BUSINESS = "BusinessPanel.png"; 
    public static final String HUD_LAYER_ID = "HUD"; 
    public static final String PANEL_NAME = "name_panel";
    public static final String BUTTON_NAME = "name";
    public static final String TEXT_DATE = "date";
    public static final String TEXT_MONEY = "money";

    public enum ScreenState {
        START_GAME,
        NEW_GAME,
        GAME
    }

    private HashMap<ScreenState, String> screenStateMap; 

    //Other
    private String chosenDepartment = "Not chosen";
    private ScreenState screenState = ScreenState.START_GAME;
    private int screenResolution;

    private HashMap<String, String> startingDepartmentsButtons;

    public Gui(WorldConquest wc) {
        this.wc = wc;
        startingDepartmentsButtons = new HashMap<>();
        screenStateMap = new HashMap<>();
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
        AppSettings settings = wc.getContext().getSettings();
        screenResolution = settings.getHeight();

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
        if (screenResolution == 1440) {
            return font1440;
        } else if (screenResolution == 1080) {
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

                                control(new ButtonBuilder(BUTTON_BASIC_ORE, "Basic Ore Mining") {
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

                                control(new ButtonBuilder(BUTTON_BUS_TRANSPORT, "Bus Transportation") {
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

                                control(new ButtonBuilder(BUTTON_STEEL_MANUFACTURING, "Steel Manufacturing") {
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
                                        style(scaleFont(BUTTON_STYLE_64, BUTTON_STYLE_48));
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

                        panel(new PanelBuilder() {
                            {
                                backgroundImage("Interface/Images/BusinessPanel.png");
                                height("60%");
                                width("50%");
                                alignCenter();
                                valignCenter();
                            }
                        });

                        panel(new PanelBuilder() {
                            {
                                height("20%");
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
                                                valign(VAlign.Center);
                                                width("80%");
                                                height("50%");
                                                interactOnClick("toggleBusinessPanel()");
                                            }
                                        });

                                        text(new TextBuilder(TEXT_DATE) {
                                            {
                                                text("not set");
                                                font(scaleFont(ZEN_32, ZEN_24));
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
        Screen currentScreen = nifty.getCurrentScreen();
        String screenID = currentScreen.getScreenId();

        if (screenID.equals(SCREEN_GAME)) {
            // Money
            Element moneyElement = currentScreen.findElementById(TEXT_MONEY);
            if (moneyElement == null) {
                return;
            }
            moneyElement.getRenderer(TextRenderer.class).setText(getMoneyString());

            // Date
            Element dateElement = currentScreen.findElementById(TEXT_DATE);
            if (dateElement == null) {
                return;
            }
            dateElement.getRenderer(TextRenderer.class).setText(getDate());
        }
    }

    public void updateName() {
        Screen gameScreen = nifty.getScreen(SCREEN_GAME);
        Element nameElement = gameScreen.findElementById(BUTTON_NAME + "#text");
        if (nameElement != null) {
            nameElement.getRenderer(TextRenderer.class).setText(getNameString());
        } else {
            System.out.println("name button is not found");
        }
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
        setScreenState(ScreenState.GAME);
        updateName();
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

    private String getMoneyString() {
        int money = wc.getPlayer().getMoney();

        if (money >= 1_000_000_000) {
            return String.format("%.2fB", money / 1000000000.0);
        } else if (money >= 1_000_000) {
            return String.format("%.2fM", money / 1000000.0);
        } else {
            return "$" + String.valueOf(money);
        }
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

    public String getChosenDepartment() {
        return chosenDepartment;
    }

}

