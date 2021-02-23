package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.elements.UISeparator;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.lang.reflect.Method;

public class SeparatorBlueprint implements UIBlueprint<UISeparator> {
    private final Text title;
    private final Method renderEvent;
    private final int width;
    private final int x;
    private final int y;
    private final String id;

    public SeparatorBlueprint(Text title, Method renderEvent, int width, int x, int y, String id) {
        this.title = title;
        this.renderEvent = renderEvent;
        this.width = width;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public static SeparatorBlueprint parse(Element element, UIViewBlueprint view) {
        Attributes attr = element.attributes();

        // Separator contents
        Text title = null;
        if (attr.hasKey("title")) {
            if (attr.get("loc").equals("true")) {
                title = new TranslatableText(attr.get("title"));
            } else {
                title = new LiteralText(attr.get("title"));
            }
        }

        // Separator events
        Method renderEvent = null;
        if (attr.hasKey("onrender")) {
            try {
                renderEvent = view.getControllerClass().getDeclaredMethod(attr.get("onrender"), UIView.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        // Separator transform
        int width = Integer.parseInt(attr.get("width"));
        int x = Integer.parseInt(attr.get("x"));
        int y = Integer.parseInt(attr.get("y"));

        String id = attr.hasKey("id") ? attr.get("id") : null;

        return new SeparatorBlueprint(title, renderEvent, width, x, y, id);
    }

    @Override
    public UISeparator restore(UIView<?> view) {
        UISeparator sep = new UISeparator();

        sep.title = title;
        sep.renderEvent = renderEvent;
        sep.width = width;
        sep.x = x;
        sep.y = y;
        sep.id = id;

        return sep;
    }
}
