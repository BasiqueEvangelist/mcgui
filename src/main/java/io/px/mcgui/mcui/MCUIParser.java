/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui;

import io.px.mcgui.mcui.elements.UIDocument;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.annotation.Nullable;

public class MCUIParser {

    private final static String[] validRootNames = new String[] {
            "screen"
    };

    /**
     * Parse a .mcui file from a JSoup Document.
     * @param doc A JSoup document.
     * @return A parsed MCUI document.
     */
    public static UIDocument parse(Document doc) {
        Element root = doc.body().children().first();

        @Nullable UIDocument document = null;

        Attributes attr = root.attributes();

        // Title
        if(attr.hasKey("title")) {
            if(root.attributes().get("loc").equals("true")) {
                document = new UIDocument(null, new TranslatableText(root.attributes().get("title")));
            } else {
                document = new UIDocument(null, new LiteralText(root.attributes().get("title")));
            }
        }

        if(attr.hasKey("showtitle")) {
            document.showTitle = Boolean.parseBoolean(attr.get("showtitle"));
        }

        // Events
        if(attr.hasKey("@render")) {
            document.renderEvent = attr.get("@render");
        }

        // Elements

        for (Element element : root.children()) {
            document.addElement(ElementParserRegistry.get(element.nodeName()).parse(element, document));
        }

        return document;
    }
}

