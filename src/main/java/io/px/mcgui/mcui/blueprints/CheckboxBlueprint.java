package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.elements.UICheckbox;
import io.px.mcgui.mcui.elements.UIView;
import org.jsoup.nodes.Element;

public class CheckboxBlueprint implements UIBlueprint<UICheckbox> {
    public static CheckboxBlueprint parse(Element element, UIViewBlueprint view) {
        return null;
    }

    @Override
    public UICheckbox restore(UIView<?> view) {
        return null;
    }
}
