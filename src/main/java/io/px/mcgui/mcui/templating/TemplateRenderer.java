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
            if (child.type == UIType.ROOT) return;
            if (child.type == UIType.LABEL) LabelRenderer.getInstance().render(element, (UILabel) child);
            if (child.type == UIType.BUTTON) ButtonRenderer.getInstance().render(element, (UIButton) child);
            if (child.type == UIType.SEPARATOR) SeparatorRenderer.getInstance().render(element, (UISeparator) child);
            if (child.type == UIType.TEMPLATE) TemplateRenderer.getInstance().render(element, (InsertedTemplate<?>) child);
        });
        element.nonIDElements.forEach(child -> {
            if (child.type == UIType.ROOT) return;
            if (child.type == UIType.LABEL) LabelRenderer.getInstance().render(element, (UILabel) child);
            if (child.type == UIType.BUTTON) ButtonRenderer.getInstance().render(element, (UIButton) child);
            if (child.type == UIType.SEPARATOR) SeparatorRenderer.getInstance().render(element, (UISeparator) child);
            if (child.type == UIType.TEMPLATE) TemplateRenderer.getInstance().render(element, (InsertedTemplate<?>) child);
        });
    }
}
