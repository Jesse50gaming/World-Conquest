package com.worldconquest;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
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

        // Build the screen programmatically
        nifty.addScreen("start", new ScreenBuilder("start") {
            {
                controller(Gui.this);

                layer(new LayerBuilder("layer") {
                    {
                        childLayoutVertical();
                        backgroundColor("#0000");

                        panel(new PanelBuilder("panel") {
                            {
                                width("50%");
                                height("50%");
                                alignCenter();
                                valignCenter();
                                childLayoutVertical();

                                text(new TextBuilder("title") {
                                    {
                                        text("Hello, Nifty GUI!");
                                        font("Interface/Fonts/Default.fnt");
                                        height("50px");
                                    }
                                });

                                control(new ButtonBuilder("btnClick", "Click Me!") {
                                    {
                                        height("50px");
                                        alignCenter();
                                        interactOnClick("onButtonClick()");
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

    public void onButtonClick() {
        System.out.println("Button clicked!");
    }
}
