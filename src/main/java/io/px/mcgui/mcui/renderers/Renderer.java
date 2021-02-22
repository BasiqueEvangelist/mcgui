package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UIElement;

public interface Renderer<T extends UIElement> {
    public void render(UIView document, T element);
}
