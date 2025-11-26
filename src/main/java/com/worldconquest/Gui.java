package com.worldconquest;


import java.util.HashMap;
import com.jme3.niftygui.NiftyJmeDisplay;
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
import de.lessvoid.nifty.spi.render.RenderFont;
import de.lessvoid.nifty.tools.Color;

public class Gui implements ScreenController {
    private WorldConquest wc;
    private Nifty nifty;
    private final String zen96 = "Interface/Fonts/96/ZenDots96.fnt";
    private final String zen64 = "Interface/Fonts/64/ZenDots64.fnt";
    private final String zen32 = "Interface/Fonts/32/ZenDots32.fnt";
    private final String zen16 = "Interface/Fonts/16/ZenDots16.fnt";

    private final String buttonStyle16 = "button_style16";
    private final String buttonStyle32 = "button_style32";
    private final String buttonStyle64 = "button_style64";
    private final String buttonStyle96 = "button_style96";

    private final String textFieldStyle16 = "text_field_style16";
    private final String textFieldStyle32 = "text_field_style32";
    private final String textFieldStyle64 = "text_field_style64";
    private final String textFieldStyle96 = "text_field_style96";

    

    private HashMap<String, RenderFont> fontChange = new HashMap<>();
    private HashMap<String, Color> colorChange = new HashMap<>();
    private HashMap<String, String> screenMap = new HashMap<>();

    public Gui(WorldConquest wc) {
        this.wc = wc;

        
        
    }
    
    /* 
    private void changeFont(String name, RenderFont font, String screen) {
        fontChange.put(name, font);
        screenMap.put(name, screen);
    }   
    
    private void changeColor(String name, Color color, String screen) {
        colorChange.put(name, color);
        screenMap.put(name, screen);
    }
    
    private void changeAll() {
        Set<String> fonts = fontChange.keySet();
        Set<String> colors = colorChange.keySet();
        for (String name : fonts) {
    
            NiftyControl control = nifty.getScreen(screenMap.get(name)).findNiftyControl(name, NiftyControl.class);
            if (control == null) continue;
            if (control.getElement() == null) continue;
            TextRenderer textRenderer = control.getElement().getRenderer(TextRenderer.class);
    
            if (textRenderer == null) continue;
            RenderFont renderedFont = fontChange.get(name);
            if (renderedFont == null) continue;
            textRenderer.setFont(renderedFont);
    
        }
    
        for (String name : colors) {
    
            NiftyControl control = nifty.getScreen(screenMap.get(name)).findNiftyControl(name, NiftyControl.class);
            if (control == null) continue;
            if (control.getElement() == null) continue;
            TextRenderer textRenderer = control.getElement().getRenderer(TextRenderer.class);
            if (textRenderer == null) continue;
            textRenderer.setColor(colorChange.get(name));
    
        }
    
    }
    */
   
    private void createStyles() {
        if (nifty == null) return;
        createButtonStyle(buttonStyle16, zen16);
        createButtonStyle(buttonStyle32, zen32);
        createButtonStyle(buttonStyle64, zen64);
        createButtonStyle(buttonStyle96, zen96);
        
        createTextFieldStyle(textFieldStyle16, zen16);
        createTextFieldStyle(textFieldStyle32, zen32);
        createTextFieldStyle(textFieldStyle64, zen64);
        createTextFieldStyle(textFieldStyle96, zen96);
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
                backgroundColor("#ffffffff"); // white cursor
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
                backgroundColor("#272727ff"); // semi-transparent
            }
        }.build(nifty);
    }

    private void createButtonStyle(String id, String fontFile) {
        // Root button style
        new StyleBuilder() {
            {
                this.id(id);
                childLayoutCenter();
                backgroundImage("Interface/Images/ButtonBackground.png");
                visibleToMouse();
            }
        }.build(nifty);

        // Internal panel
        new StyleBuilder() {
            {
                id(id + "#panel");
                childLayoutCenter();
                backgroundColor("#00000000");
            }
        }.build(nifty);

        // Text element
        new StyleBuilder() {
            {
                id(id + "#text");
                font(fontFile);
                color("#ffffffff");
            }
        }.build(nifty);

        // Focus state
        new StyleBuilder() {
            {
                id(id + "#focus");
                backgroundColor("rgba(22, 24, 24, 1)");
            }
        }.build(nifty);

        // Disabled state
        new StyleBuilder() {
            {
                id(id + "#disabled");
                color("#000000ff");
            }
        }.build(nifty);
    }

    public void initGui() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(wc.getAssetManager(), wc.getInputManager(), wc.getAudioRenderer(), wc.getGuiViewPort());
        wc.getGuiViewPort().addProcessor(niftyDisplay);
        nifty = niftyDisplay.getNifty();
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        //nifty.setDebugOptionPanelColors(true);

        //createFont();

        createStyles();

        nifty.addScreen("start", new ScreenBuilder("start") {
            {
                controller(Gui.this);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutVertical();

                        panel(new PanelBuilder() {
                            {
                                height("5%");
                            }
                        });

                        // Title Panel
                        panel(new PanelBuilder("title_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder("title") {
                                    {
                                        text("World Conquest");
                                        font(zen96);

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

                        // load game Button Panel
                        panel(new PanelBuilder("load_game_button_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();
                                backgroundColor("#3D3232FF");

                                control(new ButtonBuilder("load_game_button", "Load Game") {
                                    {
                                        style(buttonStyle64);
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

                        // new game Button Panel
                        panel(new PanelBuilder("new_game_button_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new ButtonBuilder("new_game_button", "New Game") {
                                    {
                                        height("100%");
                                        width("100%");
                                        style(buttonStyle32);
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
        // new game creation
        nifty.addScreen("new_game", new ScreenBuilder("new_game") {
            {
                controller(Gui.this);

                layer(new LayerBuilder() {
                    {
                        childLayoutVertical();
                        // new game title
                        panel(new PanelBuilder("new_game_title_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder("new_game_title") {
                                    {
                                        text("New Game");
                                        font(zen16);
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
                        panel(new PanelBuilder("back_button_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new ButtonBuilder("back_button", "Back") {
                                    {
                                        height("100%");
                                        width("100%");
                                        style(buttonStyle64);
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

                        //name input
                        panel(new PanelBuilder("name_input_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new TextFieldBuilder("name_input") {
                                    {
                                        
                                        height("100%");
                                        width("100%");
                                        style(textFieldStyle64);
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

                        // start game
                        panel(new PanelBuilder("start_game_button_panel") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                control(new ButtonBuilder("start_game_button", "Start New Game") {
                                    {
                                        style(buttonStyle64);
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
        //Game
        nifty.addScreen("game", new ScreenBuilder("game") {
            {
                controller(Gui.this);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutVertical();

                        panel(new PanelBuilder("panel") {
                            {
                                width("100%");
                                height("100%");
                                alignCenter();
                                valignCenter();
                                childLayoutHorizontal();

                                text(new TextBuilder("money") {
                                    {
                                        alignLeft();
                                        valign(VAlign.Bottom);
                                        text("not set");
                                        font(zen16);
                                        width("100px");

                                    }
                                });

                            }
                        });

                    }
                });
            }
        }.build(nifty));
        nifty.gotoScreen("start");

    }
    

    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
    }

    @Override
    public void onStartScreen() {
        /*
        wc.enqueue(() -> {
            changeAll();
            return null; 
        });
        */
    }

    @Override
    public void onEndScreen() {
    }

    public void update() {
        Element moneyElement = nifty.getCurrentScreen().findElementByName("money");
        if (moneyElement == null) return;
        moneyElement.getRenderer(TextRenderer.class).setText(getMoney());

    }

    public void startNewGame() {

        wc.startNewGame();
        nifty.gotoScreen("game");
    }
    
    public void newGameScreen() {
        nifty.gotoScreen("new_game");
    }

    public void backToStart() {
        nifty.gotoScreen("start");
    }

    public Nifty getNifty() {
        return nifty;
    }

    private String getMoney() {
        return Integer.toString(wc.getPlayer().getMoney());
    }
}
