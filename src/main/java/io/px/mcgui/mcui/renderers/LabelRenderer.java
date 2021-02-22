package io.px.mcgui.mcui.renderers;

import io.px.mcgui.exceptions.RegistryNotFoundException;
import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.MethodsRegistry;
import io.px.mcgui.mcui.elements.UIDocument;
import io.px.mcgui.mcui.elements.UILabel;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceLabelWidget;

public class LabelRenderer implements Renderer<UILabel> {
    public static LabelRenderer getInstance() {
        return new LabelRenderer();
    }
    @Override
    public void render(UIDocument document, UILabel label) {
        SpruceLabelWidget tmp = new SpruceLabelWidget(Position.of(label.x, label.y), label.getContentsAsText(), label.fixedWidth);
        tmp.setVisible(true);

        try {
            MethodsRegistry.fetch(label.renderEvent).invoke(label, document, null);
        } catch (RegistryNotFoundException e) {
            e.printStackTrace();
        }

        document.add(tmp);
        Logger.info("Registered label with content - " + label.contents);
    }
}
