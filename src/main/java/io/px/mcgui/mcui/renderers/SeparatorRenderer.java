package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UITemplate;
import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UISeparator;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceSeparatorWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class SeparatorRenderer implements Renderer<UISeparator> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/ButtonRenderer");

    public static SeparatorRenderer getInstance() {
        return new SeparatorRenderer();
    }
    @Override
    public void render(UIView document, UISeparator separator) {

        Object controller = document.getControllerInstance();

        if (separator.parent instanceof UITemplate) {
            controller = ((UITemplate) separator.parent).getControllerInstance(controller);
        }

        if(separator.renderEvent != null) {
            try {
                separator.renderEvent.invoke(controller, document, separator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        SpruceSeparatorWidget tmp = new SpruceSeparatorWidget(Position.of(separator.x, separator.y), separator.width, separator.title);
        tmp.setVisible(true);
        document.add(tmp);
        LOGGER.debug("Registered separator.");
    }
}
