package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

import java.lang.reflect.Method;

public class ButtonBlueprint implements UIBlueprint<UIButton> {
    private final Text contents;
    private final Method onClick;
    private final Method renderEvent;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String id;

    public ButtonBlueprint(Text contents, Method onClick, Method renderEvent, int x, int y, int width, int height, String id) {
        this.contents = contents;
        this.onClick = onClick;
        this.renderEvent = renderEvent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public static ButtonBlueprint parse(Element element, @Nullable UIViewBlueprint view) {
        Attributes attr = element.attributes();

        // Button contents
        Text contents;
        if (attr.get("loc").equals("true")) {
            contents = new TranslatableText(element.text());
        } else {
            contents = new LiteralText(element.text());
        }

        // Button events
        Method onClick = null;
        if (attr.hasKey("onclick")) {
            try {
                onClick = view.getControllerClass().getDeclaredMethod(attr.get("onclick"), UIView.class, UIButton.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        Method renderEvent = null;
        if (attr.hasKey("onrender")) {
            try {
                renderEvent = view.getControllerClass().getDeclaredMethod(attr.get("onrender"), UIView.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        // Button transform
        int x = Integer.parseInt(attr.get("x"));
        int y = Integer.parseInt(attr.get("y"));
        int width = Integer.parseInt(attr.get("width"));
        int height = Integer.parseInt(attr.get("height"));

        String id = attr.hasKey("id") ? attr.get("id") : null;

        return new ButtonBlueprint(contents, onClick, renderEvent, x, y, width, height, id);
    }

    @Override
    public UIButton restore(UIView<?> view) {
        UIButton btn = new UIButton();

        btn.contents = contents;
        btn.onClick = onClick;
        btn.renderEvent = renderEvent;
        btn.x = x;
        btn.y = y;
        btn.width = width;
        btn.height = height;
        btn.id = id;

        return btn;
    }
}
