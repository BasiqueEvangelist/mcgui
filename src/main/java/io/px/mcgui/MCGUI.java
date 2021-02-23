package io.px.mcgui;

import io.px.mcgui.mcui.DocumentManager;
import io.px.mcgui.mcui.elements.UIView;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

/**
 * A utility class that is a mirror for regularly used methods.
 */
public final class MCGUI {
    /**
     * Fetch a UIView.
     *
     * @param id The identifier, usually modid:filename
     * @return The UIView, can be shown using MCGUI.open(UIView view)
     */
    public static UIView fetch(Identifier id) {
        return DocumentManager.INSTANCE.fetch(id);
    }

    /**
     * Open a UIView.
     *
     * @param view The UIView to open.
     */
    @Environment(EnvType.CLIENT)
    public static void open(UIView view) {
        MinecraftClient.getInstance().openScreen(view);
    }

    /**
     * Open a UIView
     *
     * @param id The identifier, usually modid:filename
     * @return The UIView that was opened.
     */
    @Environment(EnvType.CLIENT)
    public static UIView open(Identifier id) {
        UIView view = DocumentManager.INSTANCE.fetch(id);
        MinecraftClient.getInstance().openScreen(view);
        return view;
    }
}
