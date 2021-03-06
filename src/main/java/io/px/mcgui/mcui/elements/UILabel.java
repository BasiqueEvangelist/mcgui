/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui.elements;

import net.minecraft.text.Text;

public class UILabel extends UIElement {

    public Text contents;
    public int fixedWidth;

    public String getContents() {
        return contents.asString();
    }

    public Text getContentsAsText() {
        return contents;
    }
}
