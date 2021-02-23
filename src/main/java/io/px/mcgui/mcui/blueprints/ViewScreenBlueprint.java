package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.elements.ViewScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ViewScreenBlueprint<C> implements UIViewBlueprint {
    public List<UIBlueprint<?>> elements = new ArrayList<>();
    public Class<C> controller;
    public Text title;
    public boolean showTitle;
    public Method renderEvent;

    @Override
    public Class<C> getControllerClass() {
        return controller;
    }

    public ViewScreen<C> restore(@Nullable Screen parent) {
        ViewScreen<C> screen = new ViewScreen<>(parent, title);

        screen.controller = controller;
        screen.showTitle = showTitle;
        screen.renderEvent = renderEvent;
        for (UIBlueprint<?> element : elements) {
            screen.addElement(element.restore(screen));
        }

        return screen;
    }
}
