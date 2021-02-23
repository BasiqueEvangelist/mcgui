package io.px.mcgui;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.client.gui.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Controller {
    protected UIView view;

    public Controller() {
    }

    /**
     * Add a UIElement to the linked view.
     *
     * @param element The element to add.
     * @param <T>     The type.
     * @return The element.
     */
    public <T extends Element> T add(@NotNull T element) {
        this.view.add(element);
        return element;
    }

    /**
     * Get an element by its ID
     *
     * @param id  The ID of the element.
     * @param <T> The type.
     * @return The element or null if the element doesn't exist.
     */
    @Nullable
    public <T extends UIElement> T get(@NotNull String id) {
        UIElement element = this.view.IDElements.get(id);
        return (T) element;
    }
}
