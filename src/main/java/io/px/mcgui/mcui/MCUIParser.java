/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui;

import io.px.mcgui.mcui.elements.UITemplate;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.HashSet;

public class MCUIParser {

    private final static String[] validRootNames = new String[]{
            "screen"
    };

    /**
     * Parse a .mcui file from a JSoup Document.
     *
     * @param doc A JSoup document.
     * @return A parsed MCUI document.
     */
    public static UIView parse(Document doc) {
        Element root = doc.body().children().first();

        @Nullable UIView document = null;

        Attributes attr = root.attributes();

        // Title
        if (attr.hasKey("title")) {
            if (root.attributes().get("loc").equals("true")) {
                document = new UIView(null, new TranslatableText(root.attributes().get("title")));
            } else {
                document = new UIView(null, new LiteralText(root.attributes().get("title")));
            }
        }

        if (attr.hasKey("controller")) {
            try {
                Class<?> c = Class.forName(attr.get("controller"));
                document.controller = c;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (attr.hasKey("showtitle")) {
            document.showTitle = Boolean.parseBoolean(attr.get("showtitle"));
        }

        // Events
        if (attr.hasKey("onrender")) {
            try {
                if (document.controller != null) {
                    document.renderEvent = document.controller.getDeclaredMethod(attr.get("onrender"), UIView.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Elements

        for (Element element : root.children()) {
            document.addElement(ElementParserRegistry.get(element.nodeName()).parse(element, document));
        }

        return document;
    }

    /**
     * Parse a .mcui file from a JSoup Document.
     *
     * @param doc A JSoup document.
     * @return A parsed MCUI document.
     */
    public static UITemplate parseTemplate(Document doc) {
        Element root = doc.body().children().first();

        UITemplate template = new UITemplate();

        template.elements = new HashSet<>(root.children());

        return template;
    }
}

