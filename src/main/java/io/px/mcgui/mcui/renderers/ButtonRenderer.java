package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.templating.UITemplate;
import io.px.mcgui.mcui.elements.UIView;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ButtonRenderer implements Renderer<UIButton> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/ButtonRenderer");

    public static ButtonRenderer getInstance() {
        return new ButtonRenderer();
    }

    @Override
    public void render(UIView document, UIButton button) {

        Object controller = document.getControllerInstance();

        if (button.parent instanceof UITemplate) {
            controller = ((UITemplate) button.parent).getControllerInstance(controller);
        }

        Object finalController = controller;
        SpruceButtonWidget tmp = new SpruceButtonWidget(Position.of(button.x, button.y), button.width, button.height, button.getContentsAsText(), btn -> {
            button.rendered = btn;
            if (button.onClick != null) {
                try {
                    button.onClick.invoke(finalController, document, button);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button.rendered = tmp;

        if (button.renderEvent != null) {
            try {
                button.renderEvent.invoke(controller, document, button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        tmp.setVisible(true);
        document.add(tmp.asVanilla());
        LOGGER.debug("Registered button with action - {}", button.onClick);
    }
}
