package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.elements.UILabel;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.lang.reflect.Method;

public class LabelBlueprint implements UIBlueprint<UILabel> {
    private final Text contents;
    private final Method renderEvent;
    private final int fixedWidth;
    private final int x;
    private final int y;
    private final String id;

    public LabelBlueprint(Text contents, Method renderEvent, int fixedWidth, int x, int y, String id) {
        this.contents = contents;
        this.renderEvent = renderEvent;
        this.fixedWidth = fixedWidth;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public static LabelBlueprint parse(Element element, UIViewBlueprint view) {
        Attributes attr = element.attributes();

        // Label contents
        Text contents;
        if (attr.get("loc").equals("true")) {
            contents = new TranslatableText(element.text());
        } else {
            contents = new LiteralText(element.text());
        }

        // Label events
        Method renderEvent = null;
        if (attr.hasKey("onrender")) {
            try {
                renderEvent = view.getControllerClass().getDeclaredMethod(attr.get("onrender"), UIView.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        // Label transform
        int fixedWidth = Integer.parseInt(attr.get("fixedwidth"));
        int x = Integer.parseInt(attr.get("x"));
        int y = Integer.parseInt(attr.get("y"));

        String id = attr.hasKey("id") ? attr.get("id") : null;

        return new LabelBlueprint(contents, renderEvent, fixedWidth, x, y, id);
    }

    @Override
    public UILabel restore(UIView<?> view) {
        UILabel lbl = new UILabel();

        lbl.contents = contents;
        lbl.renderEvent = renderEvent;
        lbl.fixedWidth = fixedWidth;
        lbl.x = x;
        lbl.y = y;
        lbl.id = id;

        return lbl;
    }
}
