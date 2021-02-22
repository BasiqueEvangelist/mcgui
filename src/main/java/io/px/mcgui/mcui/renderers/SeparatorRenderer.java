package io.px.mcgui.mcui.renderers;

import io.px.mcgui.exceptions.RegistryNotFoundException;
import io.px.mcgui.mcui.MethodsRegistry;
import io.px.mcgui.mcui.elements.UIDocument;
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
    public void render(UIDocument document, UISeparator separator) {
        try {
            MethodsRegistry.fetch(separator.renderEvent).invoke(separator, document, null);
        } catch (RegistryNotFoundException e) {
            e.printStackTrace();
        }

        SpruceSeparatorWidget tmp = new SpruceSeparatorWidget(Position.of(separator.x, separator.y), separator.width, separator.title);
        tmp.setVisible(true);
        document.add(tmp);
        LOGGER.debug("Registered separator.");
    }
}
