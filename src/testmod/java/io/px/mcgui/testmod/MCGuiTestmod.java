/**
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */
package io.px.mcgui.testmod;

import io.px.mcgui.logging.Logger;
import io.px.mcgui.mcui.MethodsRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;

public class MCGuiTestmod implements ModInitializer {
    @Override
    public void onInitialize() {
        MethodsRegistry.register("mcgui:testy_shit", (elm, doc, cbargs) -> {
            testMethod();
        });
        MethodsRegistry.register("mcgui:screen_render_test", (elm, doc, cbargs) -> {
            if (elm == null) {
                Logger.info(String.format("Screen(title=%s)@render invoked.", doc.getTitle().asString()));
                return;
            }
            Logger.info(String.format("Element(type=%s)@render invoked.", elm.type.toString()));
        });
    }

    public void testMethod() {
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new LiteralText("OwO"), new LiteralText("Hello!"));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}
