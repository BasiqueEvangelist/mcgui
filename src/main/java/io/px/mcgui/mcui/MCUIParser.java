/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui;

import io.px.mcgui.exceptions.RootElementException;
import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.elements.UIDocument;
import io.px.mcgui.mcui.parsers.ButtonParser;
import io.px.mcgui.mcui.parsers.LabelParser;
import io.px.mcgui.mcui.parsers.SeparatorParser;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import javax.annotation.Nullable;
import java.io.File;

public class MCUIParser {

    private final static String[] validRootNames = new String[] {
            "screen"
    };

    /**
     * Parse a .mcui file from a string.
     * @param mcuiFile A file object, preferably from the DocumentRegistry Class.
     * @param usedTemp If you used the ResourceHelper class, mark this as true.
     * @return A MCUI document.
     */
    public static UIDocument parse(File mcuiFile, boolean usedTemp) throws Exception {

        Element raw = Jsoup.parse(mcuiFile, "UTF-8").body().children().first();

        if(raw == null) throw new RootElementException("");

        @Nullable UIDocument document = null;

        Attributes attr = raw.attributes();

        // Title
        if(attr.hasKey("title")) {
            if(raw.attributes().get("loc").equals("true")) {
                document = new UIDocument(null, new TranslatableText(raw.attributes().get("title")));
            } else {
                document = new UIDocument(null, new LiteralText(raw.attributes().get("title")));
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
        for (Element element : raw.children()) {
            Logger.info(element.nodeName());
            if (element.nodeName().equals("label")) {
                document.addElement(LabelParser.getInstance().parse(element, document));
            }
            if (element.nodeName().equals("button")) {
                document.addElement(ButtonParser.getInstance().parse(element, document));
            }
            if (element.nodeName().equals("separator")) {
                document.addElement(SeparatorParser.getInstance().parse(element, document));
            }
        }

        // Temporary File Deletion
        if(usedTemp) mcuiFile.delete();

        return document;
    }
}

