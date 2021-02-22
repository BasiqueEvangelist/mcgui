package io.px.mcgui.testmod;

import io.px.mcgui.Controller;
import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIView;
import io.px.mcgui.mcui.elements.UILabel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.NotNull;

public class TestController extends Controller {
    public TestController() { super(); }
    public void onButtonClick(UIView view, UIButton label) {
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new LiteralText("OwO"), new LiteralText("Hello!"));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}
