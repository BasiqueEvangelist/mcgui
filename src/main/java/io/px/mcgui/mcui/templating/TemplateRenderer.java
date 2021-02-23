package io.px.mcgui.mcui.templating;

import io.px.mcgui.mcui.ElementParserRegistry;
import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.renderers.Renderer;
import io.px.mcgui.mcui.elements.UIView;
import org.jsoup.nodes.Element;

public class TemplateRenderer implements Renderer<UITemplate> {
    public static Renderer<UITemplate> getInstance() {
        return new TemplateRenderer();
    }

    @Override
    public void render(UIView document, UITemplate element) {
        for (Element elements : element.elements) {
            System.out.println(elements.nodeName());
            UIElement finalElement = (UIElement) ElementParserRegistry.get(elements.nodeName()).parse(elements, document);
            finalElement.x += element.x;
            finalElement.y += element.y;
            finalElement.parent = element;
            document.addElement(finalElement);
        }
    }
}
