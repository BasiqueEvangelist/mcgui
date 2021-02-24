/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui;

import io.px.mcgui.mcui.blueprints.BlueprintParserRegistry;
import io.px.mcgui.mcui.blueprints.ViewScreenBlueprint;
import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.templating.TemplateBlueprint;
import io.px.mcgui.mcui.toasts.ToastBlueprint;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
    @SuppressWarnings("unchecked")
    public static ViewScreenBlueprint<?> parseScreen(Document doc) {
        Element root = doc.body().children().first();

        ViewScreenBlueprint<Object> screen = new ViewScreenBlueprint<>();

        Attributes attr = root.attributes();

        // Title
        if (attr.hasKey("title")) {
            if (root.attributes().get("loc").equals("true")) {
                screen.title = new TranslatableText(root.attributes().get("title"));
            } else {
                screen.title = new LiteralText(root.attributes().get("title"));
            }
        }

        if (attr.hasKey("controller")) {
            try {
                screen.controller = (Class<Object>) Class.forName(attr.get("controller"));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if (attr.hasKey("showtitle")) {
            screen.showTitle = Boolean.parseBoolean(attr.get("showtitle"));
        }

        // Events
        if (attr.hasKey("onrender")) {
            if (screen.controller != null) {
                try {
                    screen.renderEvent = screen.controller.getDeclaredMethod(attr.get("onrender"), UIView.class);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Elements

        for (Element element : root.children()) {
            screen.elements.add(BlueprintParserRegistry.get(element.nodeName()).parse(element, screen));
        }

        return screen;
    }

    /**
     * Parse a .mcui file from a JSoup Document.
     *
     * @param doc A JSoup document.
     * @return A parsed MCUI document.
     */
    @SuppressWarnings("unchecked")
    public static TemplateBlueprint<?> parseTemplate(Document doc) {
        Element root = doc.body().children().first();

        TemplateBlueprint<Object> template = new TemplateBlueprint<>();

        Attributes attr = root.attributes();

        if (attr.hasKey("controller")) {
            try {
                template.controller = (Class<Object>) Class.forName(attr.get("controller"));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        for (Element element : root.children()) {
            template.elements.add(BlueprintParserRegistry.get(element.nodeName()).parse(element, template));
        }

        return template;
    }

    public static ToastBlueprint parseToast(Document doc) {
        Element root = doc.body().children().first();
        Attributes attr = root.attributes();

        Text title;
        Text contents = null;

        if(attr.get("lang").equals("true")) {
            title = new TranslatableText(attr.get("title"));
            if(root.hasText()) {
                contents = new TranslatableText(root.text());
            }
        } else {
            title = new LiteralText(attr.get("title"));
            if(root.hasText()) {
                contents = new LiteralText(root.text());
            }
        }

        return new ToastBlueprint(SystemToast.Type.valueOf(attr.get("type")), title, contents);
    }
}

