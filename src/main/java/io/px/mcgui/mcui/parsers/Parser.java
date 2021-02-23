package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIView;
import org.jsoup.nodes.Element;

public interface Parser<T extends UIElement> {
    T parse(Element element, UIView doc);
}
