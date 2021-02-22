/**
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */
package io.px.mcgui.testmod;

import io.px.mcgui.mcui.MethodsRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MCGuiTestmod implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("MCGuiTestMod");

    @Override
    public void onInitialize() {
        MethodsRegistry.register("mcgui:testy_shit", (elm, doc, cbargs) -> {
            testMethod();
        });
        MethodsRegistry.register("mcgui:screen_render_test", (elm, doc, cbargs) -> {
            if (elm == null) {
                LOGGER.info("Screen(title={})@render invoked.", doc.getTitle().asString());
                return;
            }
            LOGGER.info("Element(type={})@render invoked.", elm.type.toString());
        });
    }

    public void testMethod() {
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new LiteralText("OwO"), new LiteralText("Hello!"));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}
