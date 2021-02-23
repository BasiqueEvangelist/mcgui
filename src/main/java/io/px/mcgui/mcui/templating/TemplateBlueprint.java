package io.px.mcgui.mcui.templating;

import io.px.mcgui.mcui.blueprints.UIBlueprint;
import io.px.mcgui.mcui.blueprints.UIViewBlueprint;

import java.util.ArrayList;
import java.util.List;

public class TemplateBlueprint<T> implements UIViewBlueprint {
    public Class<T> controller;
    public List<UIBlueprint<?>> elements = new ArrayList<>();

    @Override
    public Class<T> getControllerClass() {
        return controller;
    }
}
