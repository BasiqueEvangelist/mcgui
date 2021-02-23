package io.px.mcgui.mcui.toasts;

import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class UIToast extends SystemToast {
    public UIToast(Type type, Text title, @Nullable Text description) {
        super(type, title, description);
    }
}
