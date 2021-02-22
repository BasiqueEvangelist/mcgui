package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.TemplateManager;
import io.px.mcgui.mcui.elements.UITemplate;
import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.renderers.TemplateRenderer;
import net.minecraft.util.Identifier;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.util.Objects;

public class TemplateParser implements Parser<UITemplate> {
    @Override
    public UITemplate parse(Element element, UIView doc) {
        UITemplate template = new UITemplate();

        Attributes attr = element.attributes();

        template.elements = TemplateManager.INSTANCE.fetch(new Identifier(attr.get("type").split(":")[0], attr.get("type").split(":")[1])).elements;
        template.x = Integer.parseInt(attr.get("x"));
        template.y = Integer.parseInt(attr.get("y"));

        TemplateRenderer.getInstance().render(doc, template);

        return template;
    }
}
