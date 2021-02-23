package io.px.mcgui.mcui.templating;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIType;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.client.gui.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InsertedTemplate<C> extends UIElement implements UIView<C> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/InsertedTemplate");

    public InsertedTemplate() {
        this.type = UIType.TEMPLATE;
    }

    public Class<C> controller;
    public UIView<?> parent;

    @Override
    public Class<C> getControllerClass() {
        return controller;
    }

    @Override
    public C getControllerInstance() {
        if (controller != null) {
            try {
                return controller.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public List<UIElement> nonIDElements = new ArrayList<>();
    public HashMap<String, UIElement> IDElements = new HashMap<>();

    public void addElement(UIElement element) {
        element.x += x;
        element.y += y;

        if (element.id == null) {
            System.out.println(element);
            nonIDElements.add(element);
            LOGGER.warn("Added element did not have an ID!");
        } else {
            IDElements.put(element.id, element);
        }
    }

    @Override
    public <T extends Element> T addMinecraftElement(T element) {
        parent.addMinecraftElement(element);
        return element;
    }
}
