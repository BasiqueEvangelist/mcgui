package io.px.mcgui.mcui.parsers;

import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class ButtonParser implements Parser<UIButton> {
    public static ButtonParser getInstance() {
        return new ButtonParser();
    }
    public UIButton parse(Element element, UIView doc) {
        UIButton btn = new UIButton();

        Attributes attr = element.attributes();

        // Button contents
        if(attr.get("loc").equals("true")) {
            btn.contents = new TranslatableText(element.text());
        } else {
            btn.contents = new LiteralText(element.text());
        }

        // Button events
        if(attr.hasKey("onclick")) {
            try {
                btn.onClick = doc.controller.getDeclaredMethod(attr.get("onclick"), new Class[]{ UIView.class, UIButton.class });
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if(attr.hasKey("onrender")) {
            try {
                btn.renderEvent = doc.controller.getDeclaredMethod(attr.get("onrender"), new Class[]{ UIView.class });
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        // Button transform
        btn.x = Integer.parseInt(attr.get("x"));
        btn.y = Integer.parseInt(attr.get("y"));
        btn.width = Integer.parseInt(attr.get("width"));
        btn.height = Integer.parseInt(attr.get("height"));

        if(attr.hasKey("id")) btn.id = attr.get("id");

        return btn;
    }
}
