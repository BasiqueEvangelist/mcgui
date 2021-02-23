package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIView;

public interface UIBlueprint<T extends UIElement> {
    T restore(UIView<?> view);
}
