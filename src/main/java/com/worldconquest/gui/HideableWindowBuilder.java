package com.worldconquest.gui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.window.builder.WindowBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

public class HideableWindowBuilder {

    private final String panelId;
    private final String title;
    private String startingX;
    private String startingY;
    private Element window;
    private String width;
    private String height;

    public HideableWindowBuilder(String panelId, String title, String startingX, String startingY,String height, String width) {
        this.panelId = panelId;
        this.title = title;
        this.startingX = startingX;
        this.startingY = startingY;
        this.height = height;
        this.width = width;
    }

    public Element build(Nifty nifty, Screen screen, Element parent) {

        window = new WindowBuilder(panelId, title) {
            {
                x(startingX);
                y(startingY);
                width(width);
                height(height);
                visible(false);
                backgroundImage(Gui.BUSINESS_PANEL_IMAGE);
                childLayoutVertical();

                // Disable default close button
                closeable(false);

                // Header with custom hide button
                panel(new PanelBuilder(panelId + "_header") {
                    {
                        height("8%");
                        childLayoutHorizontal();

                        panel(new PanelBuilder() {
                            {
                                width("95%");
                            }
                        });
                        control(new ButtonBuilder(panelId + "_closeBtn", "X") {
                            {
                                width("5%");
                                height("100%");
                                alignRight();
                                interactOnClick("toggleWindow(department)");
                            }
                        });
                    }
                });

                // Content area
                panel(new PanelBuilder(panelId + "_content") {
                    {
                        height("92%");
                        childLayoutVertical();
                    }
                });
            }
        }.build(nifty, screen, parent);

        return window;
    }

    public Element getWindow() {
        return window;
    }
}
