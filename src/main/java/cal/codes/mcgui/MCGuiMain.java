/**
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */
package cal.codes.mcgui;

import cal.codes.mcgui.logging.Logger;
import cal.codes.mcgui.mcui.DocumentRegistry;
import cal.codes.mcgui.mcui.MCUIParser;
import cal.codes.mcgui.mcui.MethodsRegistry;
import cal.codes.mcgui.utils.ResourceHelper;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;

public class MCGuiMain implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
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
            DocumentRegistry.register("mcgui:test_file", MCUIParser.parse(ResourceHelper.getFileFromResource("assets/mcgui/test.mcui", MCGuiMain.class), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testMethod() {
        SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT,
                new LiteralText("OwO"), new LiteralText("Hello!"));
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}
