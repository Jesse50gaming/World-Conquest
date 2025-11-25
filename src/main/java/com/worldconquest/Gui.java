package com.worldconquest;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Gui implements ScreenController {
    private WorldConquest wc;
    private Nifty nifty;

    public Gui(WorldConquest wc) {
        this.wc = wc;
        
    }

    public void initGui() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(wc.getAssetManager(), wc.getInputManager(), wc.getAudioRenderer(), wc.getGuiViewPort());
        wc.getGuiViewPort().addProcessor(niftyDisplay);
        nifty = niftyDisplay.getNifty();
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        //nifty.setDebugOptionPanelColors(true);
       
        nifty.addScreen("start", new ScreenBuilder("start") {
            {
                controller(Gui.this);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutVertical();
                        
                        panel( new PanelBuilder() {
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
                                        font("Interface/Fonts/Default.fnt");
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
                                        height("100%");
                                        width("100%");
                                        font("Interface/Fonts/Default.fnt");
                                        alignCenter();
                                        interactOnClick("startGame()");
                                        backgroundColor("#3D3232FF");
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
                                backgroundColor("#3D3232FF");

                                control(new ButtonBuilder("new_game_button", "New Game") {
                                    {
                                        height("100%");
                                        width("100%");
                                        font("Interface/Fonts/Default.fnt");
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
                        panel(new PanelBuilder("new_game_title") {
                            {
                                width("20%");
                                height("10%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder("new_game_title") {
                                    {
                                        text("New Game");
                                        font("Interface/Fonts/Default.fnt");
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
                                backgroundColor("#3D3232FF");

                                control(new ButtonBuilder("back_button", "Back") {
                                    {
                                        height("100%");
                                        width("100%");
                                        font("Interface/Fonts/Default.fnt");
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
                                backgroundColor("#3D3232FF");

                                control(new TextFieldBuilder("name_input") {
                                    {
                                        height("100%");
                                        width("100%");
                                        font("Interface/Fonts/Default.fnt");
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
                                backgroundColor("#3D3232FF");

                                control(new ButtonBuilder("start_game_button", "Start New Game") {
                                    {
                                        height("100%");
                                        width("100%");
                                        font("Interface/Fonts/Default.fnt");
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
                                        text("Money");
                                        font("Interface/Fonts/Default.fnt");
                                        width("100px");
                                        
                                    }
                                });
                                text(new TextBuilder("not money") {
                                    {
                                        alignLeft();
                                        valign(VAlign.Bottom);
                                        text("Not money");
                                        font("Interface/Fonts/Default.fnt");
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
    }

    @Override
    public void onEndScreen() {
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
}
