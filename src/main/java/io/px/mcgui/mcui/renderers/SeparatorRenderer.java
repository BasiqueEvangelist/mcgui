package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UISeparator;
import io.px.mcgui.mcui.templating.UITemplate;
import io.px.mcgui.mcui.elements.ViewScreen;
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
    public void render(ViewScreen screen, UISeparator separator) {

        Object controller = screen.getControllerInstance();

        if (separator.parent instanceof UITemplate) {
            controller = ((UITemplate) separator.parent).getControllerInstance(controller);
        }

        SpruceSeparatorWidget tmp = new SpruceSeparatorWidget(Position.of(separator.x, separator.y), separator.width, separator.title);

        separator.rendered = tmp;

        if (separator.renderEvent != null) {
            try {
                separator.renderEvent.invoke(controller, screen, separator);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        tmp.setVisible(true);
        screen.add(tmp);
        LOGGER.debug("Registered separator.");
    }
}
