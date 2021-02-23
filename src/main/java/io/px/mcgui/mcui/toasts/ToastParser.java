package io.px.mcgui.mcui.toasts;

import io.px.mcgui.mcui.elements.ViewScreen;
import io.px.mcgui.mcui.parsers.Parser;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class ToastParser implements Parser<UIToast> {
    public static ToastParser getInstance() {
        return new ToastParser();
    }

    @Override
    public UIToast parse(Element element, ViewScreen screen) {
        Attributes attr = element.attributes();

        Text title;
        Text contents = null;

        if(attr.get("lang") == "true") {
            title = new TranslatableText(attr.get("title"));
            if(element.hasText()) {
                contents = new TranslatableText(element.text());
            }
        } else {
            title = new LiteralText(attr.get("title"));
            if(element.hasText()) {
                contents = new LiteralText(element.text());
            }
        }

        UIToast toast = new UIToast(SystemToast.Type.valueOf(attr.get("type")), title, contents);
        return toast;
    }
}
