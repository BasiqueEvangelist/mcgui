package io.px.mcgui.mcui.blueprints;

import org.jsoup.nodes.Element;

public interface BlueprintParser<T extends UIBlueprint<?>> {
    T parse(Element element, UIViewBlueprint view);
}
