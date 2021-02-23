package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIView;

public interface Renderer<T extends UIElement> {
    void render(UIView document, T element);
}
