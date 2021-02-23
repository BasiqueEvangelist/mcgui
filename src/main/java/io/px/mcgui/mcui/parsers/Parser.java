package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.ViewScreen;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

public interface Parser<T> {
    T parse(Element element, @Nullable ViewScreen screen);
}
