/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui.elements;

import net.minecraft.text.Text;

import java.lang.reflect.Method;

public class UIButton extends UIElement {

    public Text contents;
    
    public Method onClick;

    public int width;
    public int height;

    public UIButton() {
        type = UIType.BUTTON;
    }

    public String getContents() {
        return contents.asString();
    }

    public Text getContentsAsText() {
        return contents;
    }

}
