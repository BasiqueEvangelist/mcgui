package io.px.mcgui.mcui.templating;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIType;
import org.jsoup.nodes.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class UITemplate extends UIElement {
    public UITemplate() {
        this.elements = new HashSet<>();
        this.type = UIType.TEMPLATE;
    }

    public Class<?> controller;

    public Object getControllerInstance(Object old) {
        if (controller != null) {
            try {
                return controller.getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return old;
    }

    public int x;
    public int y;

    public HashSet<Element> elements;
}
