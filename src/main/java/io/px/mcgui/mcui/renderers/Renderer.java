package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.ViewScreen;

public interface Renderer<T extends UIElement> {
    void render(ViewScreen screen, T element);
}
