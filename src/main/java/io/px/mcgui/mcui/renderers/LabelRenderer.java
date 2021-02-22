package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.exceptions.RegistryNotFoundException;
import io.px.mcgui.mcui.MethodsRegistry;
import io.px.mcgui.mcui.elements.UIDocument;
import io.px.mcgui.mcui.elements.UILabel;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceLabelWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LabelRenderer implements Renderer<UILabel> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/LabelRenderer");

    public static LabelRenderer getInstance() {
        return new LabelRenderer();
    }
    @Override
    public void render(UIView document, UILabel label) {
        SpruceLabelWidget tmp = new SpruceLabelWidget(Position.of(label.x, label.y), label.getContentsAsText(), label.fixedWidth);
        tmp.setVisible(true);

        if(label.renderEvent != null) {
            try {
                label.renderEvent.invoke(document.getControllerInstance(), document, label);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        document.add(tmp);
        LOGGER.debug("Registered label with content - {}", label.contents);
    }
}
