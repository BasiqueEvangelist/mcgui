/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui.elements;

import java.lang.reflect.Method;

public class UIElement {
    public String id;
    public int x;
    public int y;
    public UIType type;

    public UIElement parent;

    public Method renderEvent;

    public UIElement() {
        type = UIType.ROOT;
    }

    public String getId() {
        return id;
    }
}
