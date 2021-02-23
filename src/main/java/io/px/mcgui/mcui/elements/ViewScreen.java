/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */

package io.px.mcgui.mcui.elements;

import io.px.mcgui.mcui.renderers.ButtonRenderer;
import io.px.mcgui.mcui.renderers.LabelRenderer;
import io.px.mcgui.mcui.renderers.SeparatorRenderer;
import io.px.mcgui.mcui.templating.InsertedTemplate;
import io.px.mcgui.mcui.templating.TemplateRenderer;
import me.lambdaurora.spruceui.Tooltip;
import me.lambdaurora.spruceui.screen.SpruceScreen;
import me.lambdaurora.spruceui.widget.SpruceSeparatorWidget;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewScreen<C> extends SpruceScreen implements UIView<C> {
    private static final Logger LOGGER = LogManager.getLogger("MCGui/ViewScreen");
    public Screen parent;

    public List<UIElement> nonIDElements = new ArrayList<>();
    public Class<C> controller;

    public C getControllerInstance() {
        try {
            return controller.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<C> getControllerClass() {
        return controller;
    }

    public HashMap<String, UIElement> IDElements = new HashMap<>();

    // Title
    public boolean showTitle = true;

    // Events
    public Method renderEvent;

    public ViewScreen(@Nullable Screen parent, Text title) {
        super(title);
        this.parent = parent;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.children().stream().filter(child -> child instanceof Drawable).forEach(child -> ((Drawable) child).render(matrices, mouseX, mouseY, delta));

        super.render(matrices, mouseX, mouseY, delta);

        if (showTitle) drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 8, 16777215);

        Tooltip.renderAll(this.parent, matrices);
    }

    public <T extends Element> T addMinecraftElement(T element) {
        this.addChild(element);
        return element;
    }

    @Override
    protected void init() {
        super.init();
        if (renderEvent != null) {
            try {
                renderEvent.invoke(getControllerInstance(), this);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        IDElements.values().forEach(element -> {
            if (element instanceof UILabel) LabelRenderer.getInstance().render(this, (UILabel) element);
            else if (element instanceof UIButton) ButtonRenderer.getInstance().render(this, (UIButton) element);
            else if (element instanceof UISeparator) SeparatorRenderer.getInstance().render(this, (UISeparator) element);
            else if (element instanceof InsertedTemplate) TemplateRenderer.getInstance().render(this, (InsertedTemplate<?>) element);
        });
        nonIDElements.forEach(element -> {
            if (element instanceof UILabel) LabelRenderer.getInstance().render(this, (UILabel) element);
            else if (element instanceof UIButton) ButtonRenderer.getInstance().render(this, (UIButton) element);
            else if (element instanceof UISeparator) SeparatorRenderer.getInstance().render(this, (UISeparator) element);
            else if (element instanceof InsertedTemplate) TemplateRenderer.getInstance().render(this, (InsertedTemplate<?>) element);
        });
    }

    public void addElement(UIElement element) {
        if (element.id == null) {
            System.out.println(element);
            nonIDElements.add(element);
            LOGGER.warn("Added element did not have an ID!");
        } else {
            IDElements.put(element.id, element);
        }
    }

    public void addElements(List<UIElement> elements) {
        elements.forEach(element -> {
            addElement(element);
        });
    }

}
