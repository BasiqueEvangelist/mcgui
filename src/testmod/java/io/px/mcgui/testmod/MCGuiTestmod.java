/**
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */
package io.px.mcgui.testmod;

import io.px.mcgui.MCGUI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;

public class MCGuiTestmod implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((re, d) -> {
            re.register(CommandManager.literal("opentestgui").executes(ctx -> {
                MCGUI.openScreen(new Identifier("mcgui-testmod", "test"),  null);
                return 0;
            }));
        });
    }
}
