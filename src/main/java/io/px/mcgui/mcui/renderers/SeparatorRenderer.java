package io.px.mcgui.mcui.renderers;

import io.px.mcgui.exceptions.RegistryNotFoundException;
import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.MethodsRegistry;
import io.px.mcgui.mcui.elements.UIDocument;
import io.px.mcgui.mcui.elements.UISeparator;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceSeparatorWidget;

public class SeparatorRenderer implements Renderer<UISeparator> {
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
        Logger.info("Registered separator.");
    }
}
