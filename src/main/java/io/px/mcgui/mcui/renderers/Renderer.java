package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIDocument;
import io.px.mcgui.mcui.elements.UIElement;

public interface Renderer<T extends UIElement> {
    public void render(UIDocument document, T element);
}
