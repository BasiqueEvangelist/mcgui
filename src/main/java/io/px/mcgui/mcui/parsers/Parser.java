package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.UIDocument;
import io.px.mcgui.mcui.elements.UIElement;
import org.jsoup.nodes.Element;

public interface Parser<T extends UIElement> {
    public T parse(Element element, UIDocument doc);
}
