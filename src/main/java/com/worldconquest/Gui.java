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
        initGui();
    }

    private void initGui() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(wc.getAssetManager(), wc.getInputManager(), wc.getAudioRenderer(), wc.getGuiViewPort());
        wc.getGuiViewPort().addProcessor(niftyDisplay);
        nifty = niftyDisplay.getNifty();

       
        nifty.addScreen("start", new ScreenBuilder("start") {
            {
                controller(Gui.this);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutVertical();
                        backgroundColor("#000000ff");

                        panel(new PanelBuilder("panel") {
                            {
                                width("100%");
                                height("100%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder("Spacer") {
                                    {
                                        height("200px");
                                        alignCenter();
                                    }
                                });

                                text(new TextBuilder("title") {
                                    {
                                        text("World Conquest");
                                        font("Interface/Fonts/Default.fnt");
                                        height("50px");
                                        alignCenter();

                                    }
                                });
                                control(new TextFieldBuilder("Business Name") {
                                    {
                                        height("150px");
                                        width("400px");
                                        alignCenter();
                                        color("rgba(70, 70, 70, 1)");
                                    }
                                });

                                control(new ButtonBuilder("btnClick", "Start Game") {
                                    {
                                        height("250px");
                                        width("400px");
                                        alignCenter();
                                        interactOnClick("startGame()");
                                        color("rgba(70, 70, 70, 1)");
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

    public void startGame() {
        
        wc.startGame();
        nifty.gotoScreen("game");
    }
}
