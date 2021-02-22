package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UIElement;
import org.jsoup.nodes.Element;

public interface Parser<T extends UIElement> {
    public T parse(Element element, UIView doc);
}
