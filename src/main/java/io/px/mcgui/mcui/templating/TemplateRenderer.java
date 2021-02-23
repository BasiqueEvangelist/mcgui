package io.px.mcgui.mcui.templating;

import io.px.mcgui.mcui.elements.*;
import io.px.mcgui.mcui.renderers.ButtonRenderer;
import io.px.mcgui.mcui.renderers.LabelRenderer;
import io.px.mcgui.mcui.renderers.Renderer;
import io.px.mcgui.mcui.renderers.SeparatorRenderer;

public class TemplateRenderer implements Renderer<InsertedTemplate<?>> {
    public static Renderer<InsertedTemplate<?>> getInstance() {
        return new TemplateRenderer();
    }

    @Override
    public void render(UIView<?> view, InsertedTemplate<?> element) {
        element.IDElements.values().forEach(child -> {
            if (child instanceof UILabel) LabelRenderer.getInstance().render(element, (UILabel) child);
            else if (child instanceof UIButton) ButtonRenderer.getInstance().render(element, (UIButton) child);
            else if (child instanceof UISeparator) SeparatorRenderer.getInstance().render(element, (UISeparator) child);
            else if (child instanceof InsertedTemplate) TemplateRenderer.getInstance().render(element, (InsertedTemplate<?>) child);
        });
        element.nonIDElements.forEach(child -> {
            if (child instanceof UILabel) LabelRenderer.getInstance().render(element, (UILabel) child);
            else if (child instanceof UIButton) ButtonRenderer.getInstance().render(element, (UIButton) child);
            else if (child instanceof UISeparator) SeparatorRenderer.getInstance().render(element, (UISeparator) child);
            else if (child instanceof InsertedTemplate) TemplateRenderer.getInstance().render(element, (InsertedTemplate<?>) child);
        });
    }
}
