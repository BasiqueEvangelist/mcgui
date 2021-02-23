/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.ViewScreen;
import io.px.mcgui.mcui.templating.UITemplate;
import io.px.mcgui.mcui.toasts.ToastParser;
import io.px.mcgui.mcui.toasts.UIToast;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.annotation.Nullable;
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
    public static ViewScreen parseScreen(Document doc) {
        Element root = doc.body().children().first();

        @Nullable ViewScreen screen = null;

        Attributes attr = root.attributes();

        // Title
        if (attr.hasKey("title")) {
            if (root.attributes().get("loc").equals("true")) {
                screen = new ViewScreen(null, new TranslatableText(root.attributes().get("title")));
            } else {
                screen = new ViewScreen(null, new LiteralText(root.attributes().get("title")));
            }
        }

        if (attr.hasKey("controller")) {
            try {
                Class<?> c = Class.forName(attr.get("controller"));
                screen.controller = c;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (attr.hasKey("showtitle")) {
            screen.showTitle = Boolean.parseBoolean(attr.get("showtitle"));
        }

        // Events
        if (attr.hasKey("onrender")) {
            try {
                if (screen.controller != null) {
                    screen.renderEvent = screen.controller.getDeclaredMethod(attr.get("onrender"), ViewScreen.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Elements

        for (Element element : root.children()) {
            screen.addElement((UIElement) ElementParserRegistry.get(element.nodeName()).parse(element, screen));
        }

        return screen;
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

    public static UIToast parseToast(Document doc) {
        Element root = doc.body().children().first();

        UIToast toast = ToastParser.getInstance().parse(root, null);
        return toast;
    }
}

