package io.px.mcgui.mcui.renderers;

import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UISeparator;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceSeparatorWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeparatorRenderer implements Renderer<UISeparator> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/ButtonRenderer");

    public static SeparatorRenderer getInstance() {
        return new SeparatorRenderer();
    }
    @Override
    public void render(UIView document, UISeparator separator) {

        if(separator.renderEvent != null) {
            try {
                separator.renderEvent.invoke(document.getControllerInstance(), document, separator);
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
