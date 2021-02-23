package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UISeparator;
import io.px.mcgui.mcui.elements.UIView;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceSeparatorWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class SeparatorRenderer implements Renderer<UISeparator> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/SeparatorRenderer");

    public static SeparatorRenderer getInstance() {
        return new SeparatorRenderer();
    }

    @Override
    public void render(UIView<?> view, UISeparator separator) {
        Object controller = view.getControllerInstance();

        SpruceSeparatorWidget tmp = new SpruceSeparatorWidget(Position.of(separator.x, separator.y), separator.width, separator.title);

        separator.rendered = tmp;

        if (separator.renderEvent != null) {
            try {
                separator.renderEvent.invoke(controller, view, separator);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        tmp.setVisible(true);
        view.addMinecraftElement(tmp);
        LOGGER.debug("Registered separator.");
    }
}
