package io.px.mcgui.testmod;

import io.px.mcgui.MCGUI;
import io.px.mcgui.mcui.elements.UIButton;
import io.px.mcgui.mcui.elements.UIView;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class TestController {
    public TestController() { super(); }
    public void onButtonClick(UIView view, UIButton label) {
        MCGUI.openToast(new Identifier("mcgui-testmod", "test_toast"));
    }
}
