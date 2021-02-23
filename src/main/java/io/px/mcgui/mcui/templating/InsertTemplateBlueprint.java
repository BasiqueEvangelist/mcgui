package io.px.mcgui.mcui.templating;

import io.px.mcgui.mcui.TemplateManager;
import io.px.mcgui.mcui.blueprints.UIBlueprint;
import io.px.mcgui.mcui.blueprints.UIViewBlueprint;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.util.Identifier;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class InsertTemplateBlueprint<C> implements UIBlueprint<InsertedTemplate<C>> {
    private final int x;
    private final int y;
    private final TemplateBlueprint<C> template;

    public InsertTemplateBlueprint(int x, int y, TemplateBlueprint<C> template) {
        this.x = x;
        this.y = y;
        this.template = template;
    }

    public static <C> InsertTemplateBlueprint<C> parse(Element element, UIViewBlueprint view) {
        Attributes attr = element.attributes();

        int x = Integer.parseInt(attr.get("x"));
        int y = Integer.parseInt(attr.get("y"));
        TemplateBlueprint<C> template = TemplateManager.INSTANCE.fetch(new Identifier(attr.get("type")));

        return new InsertTemplateBlueprint<C>(x, y, template);
    }

    @Override
    public InsertedTemplate<C> restore(UIView<?> view) {
        InsertedTemplate<C> inserted = new InsertedTemplate<C>();

        inserted.x = x;
        inserted.y = y;
        inserted.parent = view;
        inserted.controller = template.controller;
        for (UIBlueprint<?> element : template.elements) {
            inserted.addElement(element.restore(inserted));
        }

        return inserted;
    }
}
