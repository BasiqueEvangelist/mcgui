package io.px.mcgui.mcui.elements;

import net.minecraft.text.Text;

public class UISeparator extends UIElement {

    public int width;
    public Text title;

    public String getTitle() {
        return title.asString();
    }

    public Text getTitleAsText() {
        return title;
    }
}
