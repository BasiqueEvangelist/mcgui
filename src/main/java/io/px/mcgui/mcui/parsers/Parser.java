package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIView;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

public interface Parser<T> {
    T parse(Element element, @Nullable UIView doc);
}
