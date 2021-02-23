package io.px.mcgui.mcui.elements;

import net.minecraft.client.gui.Element;

public interface UIView<C> {
    Class<C> getControllerClass();
    C getControllerInstance();
    <T extends Element> T addMinecraftElement(T element);
}
