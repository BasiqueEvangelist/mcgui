package io.px.mcgui.mcui.renderers;

import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIView;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceButtonWidget;

public class ButtonRenderer implements Renderer<UIButton> {
    public static ButtonRenderer getInstance() {
        return new ButtonRenderer();
    }
    @Override
    public void render(UIView document, UIButton button) {

        if(button.renderEvent != null) {
            try {
                button.renderEvent.invoke(document.getControllerInstance(), document, button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        SpruceButtonWidget tmp = new SpruceButtonWidget(Position.of(button.x, button.y), button.width, button.height, button.getContentsAsText(), btn -> {
            if(button.onClick != null) {
                try {
                    button.onClick.invoke(document.getControllerInstance(), document, button);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        tmp.setVisible(true);
        document.add(tmp.asVanilla());
        Logger.info("Registered button with action - " + button.onClick);
    }
}
