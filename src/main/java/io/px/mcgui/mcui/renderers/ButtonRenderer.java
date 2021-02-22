package io.px.mcgui.mcui.renderers;

import io.px.mcgui.exceptions.RegistryNotFoundException;
import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.MethodsRegistry;
import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIDocument;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;

public class ButtonRenderer implements Renderer<UIButton> {
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
        Logger.info("Registered button with action - " + button.onClick);
    }
}
