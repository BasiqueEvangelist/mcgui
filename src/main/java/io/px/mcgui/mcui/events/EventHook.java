package io.px.mcgui.mcui.events;

import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UIElement;
import org.jetbrains.annotations.Nullable;

public interface EventHook {
    public void invoke(@Nullable UIElement element, UIView document, @Nullable CheckboxEventArgs checkboxEventArgs);
}
