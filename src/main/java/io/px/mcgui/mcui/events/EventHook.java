package io.px.mcgui.mcui.events;

import io.px.mcgui.mcui.elements.UIElement;
import io.px.mcgui.mcui.elements.UIView;
import org.jetbrains.annotations.Nullable;

public interface EventHook {
    void invoke(@Nullable UIElement element, UIView<?> view, @Nullable CheckboxEventArgs checkboxEventArgs);
}
