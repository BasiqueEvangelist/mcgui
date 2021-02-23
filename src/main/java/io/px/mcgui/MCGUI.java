package io.px.mcgui;

import io.px.mcgui.mcui.ViewScreenManager;
import io.px.mcgui.mcui.ToastsManager;
import io.px.mcgui.mcui.elements.ViewScreen;
import io.px.mcgui.mcui.toasts.UIToast;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

/**
 * A utility class that is a mirror for regularly used methods.
 */
public final class MCGUI {
    /**
     * Fetch a ViewScreen.
     *
     * @param id The identifier, usually modid:filename
     * @return The ViewScreen, can be shown using MCGUI.open(ViewScreen screen)
     */
    public static ViewScreen fetchScreen(Identifier id) {
        return ViewScreenManager.INSTANCE.fetch(id);
    }

    /**
     * Open a ViewScreen.
     *
     * @param screen The ViewScreen to open.
     */
    @Environment(EnvType.CLIENT)
    public static void openScreen(ViewScreen screen) {
        MinecraftClient.getInstance().openScreen(screen);
    }

    /**
     * Open a ViewScreen
     *
     * @param id The identifier, usually modid:filename
     * @return The ViewScreen that was opened.
     */
    @Environment(EnvType.CLIENT)
    public static ViewScreen openScreen(Identifier id) {
        ViewScreen screen = ViewScreenManager.INSTANCE.fetch(id);
        MinecraftClient.getInstance().openScreen(screen);
        return screen;
    }

    /**
     * Fetch a UIToast
     * @param id The identifier usually modid:filename
     * @return The toast.
     */
    public static UIToast fetchToast(Identifier id) {
        return ToastsManager.INSTANCE.fetch(id);
    }

    /**
     * Fetch and show UIToast
     * @param id The identifier usually modid:filename
     * @return The toast.
     */
    @Environment(EnvType.CLIENT)
    public static UIToast openToast(Identifier id) {
        UIToast toast = ToastsManager.INSTANCE.fetch(id);
        MinecraftClient.getInstance().getToastManager().add(toast);
        return toast;
    }

    /**
     * Fetch and show UIToast
     * @param toast The toast to show.
     */
    @Environment(EnvType.CLIENT)
    public static void openToast(UIToast toast) {
        MinecraftClient.getInstance().getToastManager().add(toast);
    }
}
