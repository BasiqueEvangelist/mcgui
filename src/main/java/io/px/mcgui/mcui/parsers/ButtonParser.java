package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.ViewScreen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class ButtonParser implements Parser<UIButton> {
    public static ButtonParser getInstance() {
        return new ButtonParser();
    }

    public UIButton parse(Element element, ViewScreen screen) {
        UIButton btn = new UIButton();

        Attributes attr = element.attributes();

        // Button contents
        if (attr.get("loc").equals("true")) {
            btn.contents = new TranslatableText(element.text());
        } else {
            btn.contents = new LiteralText(element.text());
        }

        // Button events
        if (attr.hasKey("onclick")) {
            try {
                btn.onClick = screen.controller.getDeclaredMethod(attr.get("onclick"), ViewScreen.class, UIButton.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if (attr.hasKey("onrender")) {
            try {
                btn.renderEvent = screen.controller.getDeclaredMethod(attr.get("onrender"), ViewScreen.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        // Button transform
        btn.x = Integer.parseInt(attr.get("x"));
        btn.y = Integer.parseInt(attr.get("y"));
        btn.width = Integer.parseInt(attr.get("width"));
        btn.height = Integer.parseInt(attr.get("height"));

        if (attr.hasKey("id")) btn.id = attr.get("id");

        return btn;
    }
}
