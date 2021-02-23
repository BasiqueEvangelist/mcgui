package io.px.mcgui.mcui.events;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.ViewScreen;
import org.jetbrains.annotations.Nullable;

public interface EventHook {
    void invoke(@Nullable UIElement element, ViewScreen screen, @Nullable CheckboxEventArgs checkboxEventArgs);
}
