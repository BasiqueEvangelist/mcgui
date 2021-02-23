package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UILabel;
import io.px.mcgui.mcui.elements.ViewScreen;
import io.px.mcgui.mcui.templating.UITemplate;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceLabelWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class LabelRenderer implements Renderer<UILabel> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/LabelRenderer");

    public static LabelRenderer getInstance() {
        return new LabelRenderer();
    }

    @Override
    public void render(ViewScreen screen, UILabel label) {

        Object controller = screen.getControllerInstance();

        if (label.parent instanceof UITemplate) {
            controller = ((UITemplate) label.parent).getControllerInstance(controller);
        }

        SpruceLabelWidget tmp = new SpruceLabelWidget(Position.of(label.x, label.y), label.getContentsAsText(), label.fixedWidth);
        tmp.setVisible(true);

        label.rendered = tmp;

        if (label.renderEvent != null) {
            try {
                label.renderEvent.invoke(controller, screen, label);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        screen.add(tmp);
        LOGGER.debug("Registered label with content - {}", label.contents);
    }
}
