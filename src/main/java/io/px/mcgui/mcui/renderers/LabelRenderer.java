package io.px.mcgui.mcui.renderers;

import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UILabel;
import me.lambdaurora.spruceui.Position;
import me.lambdaurora.spruceui.widget.SpruceLabelWidget;

public class LabelRenderer implements Renderer<UILabel> {
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
        Logger.info("Registered label with content - " + label.contents);
    }
}
