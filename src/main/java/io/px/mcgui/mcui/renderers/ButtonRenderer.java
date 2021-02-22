package io.px.mcgui.mcui.renderers;

import io.px.mcgui.exceptions.RegistryNotFoundException;
import io.px.mcgui.mcui.MethodsRegistry;
import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIDocument;
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
    public void render(UIDocument document, UIButton button) {
        SpruceButtonWidget tmp = new SpruceButtonWidget(Position.of(button.x, button.y), button.width, button.height, button.getContentsAsText(), btn -> {
            try {
                MethodsRegistry.fetch(button.onClick).invoke(button, document, null);
            } catch (RegistryNotFoundException e) {
                e.printStackTrace();
            }
        });
        tmp.setVisible(true);
        document.add(tmp.asVanilla());
        LOGGER.debug("Registered button with action - {}", button.onClick);
    }
}
