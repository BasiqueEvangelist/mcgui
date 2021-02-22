package io.px.mcgui;

import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UIElement;
import net.minecraft.client.gui.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Controller {
    protected UIView view;

    public Controller() {}

    public <T extends Element> T add(@NotNull T element) {
        this.view.add(element);
        return element;
    }

    @Nullable
    public <T extends UIElement> T get(@NotNull String id) {
        UIElement element = this.view.IDElements.get(id);
        return (T) element;
    }
}
