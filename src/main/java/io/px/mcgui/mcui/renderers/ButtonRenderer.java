package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.ViewScreen;
import io.px.mcgui.mcui.templating.UITemplate;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class ButtonRenderer implements Renderer<UIButton> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/ButtonRenderer");

    public static ButtonRenderer getInstance() {
        return new ButtonRenderer();
    }

    @Override
    public void render(ViewScreen screen, UIButton button) {

        Object controller = screen.getControllerInstance();

        if (button.parent instanceof UITemplate) {
            controller = ((UITemplate) button.parent).getControllerInstance(controller);
        }

        Object finalController = controller;
        SpruceButtonWidget tmp = new SpruceButtonWidget(Position.of(button.x, button.y), button.width, button.height, button.getContentsAsText(), btn -> {
            button.rendered = btn;
            if (button.onClick != null) {
                try {
                    button.onClick.invoke(finalController, screen, button);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        button.rendered = tmp;

        if (button.renderEvent != null) {
            try {
                button.renderEvent.invoke(controller, screen, button);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        tmp.setVisible(true);
        screen.add(tmp.asVanilla());
        LOGGER.debug("Registered button with action - {}", button.onClick);
    }
}
