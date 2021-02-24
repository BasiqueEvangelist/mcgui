package io.px.mcgui.mcui.toasts;

import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ToastBlueprint {
    private final SystemToast.Type type;
    private final Text title;
    private final @Nullable Text description;

    public ToastBlueprint(SystemToast.Type type, Text title, @Nullable Text description) {
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public UIToast restore() {
        return new UIToast(type, title, description);
    }
}
