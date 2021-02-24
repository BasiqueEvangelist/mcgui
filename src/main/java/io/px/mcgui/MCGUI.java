package io.px.mcgui;

import io.px.mcgui.mcui.ToastsManager;
import io.px.mcgui.mcui.ViewScreenManager;
import io.px.mcgui.mcui.blueprints.ViewScreenBlueprint;
import io.px.mcgui.mcui.elements.ViewScreen;
import io.px.mcgui.mcui.toasts.ToastBlueprint;
import io.px.mcgui.mcui.toasts.UIToast;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class that is a mirror for regularly used methods.
 */
public final class MCGUI {
    /**
     * Fetch a ViewScreenBlueprint.
     *
     * @param id The identifier, usually modid:filename
     * @return The ViewScreenBlueprint, which can then be instantiated via ViewScreenBlueprint.restore
     */
    public static <C> ViewScreenBlueprint<C> fetchScreen(Identifier id) {
        return ViewScreenManager.INSTANCE.fetch(id);
    }

    /**
     * Open a ViewScreen.
     *
     * @param screen The ViewScreen to open.
     */
    @Environment(EnvType.CLIENT)
    public static void openScreen(ViewScreen<?> screen) {
        MinecraftClient.getInstance().openScreen(screen);
    }

    /**
     * Instantiates and opens a ViewScreen
     *
     * @param id The identifier, usually modid:filename
     * @return The ViewScreen that was opened.
     */
    @Environment(EnvType.CLIENT)
    public static <C> ViewScreen<C> openScreen(Identifier id, @Nullable Screen parent) {
        ViewScreen<C> screen = ViewScreenManager.INSTANCE.<C>fetch(id).restore(parent);
        MinecraftClient.getInstance().openScreen(screen);
        return screen;
    }

    /**
     * Fetch a ToastBlueprint
     * @param id The identifier usually modid:filename
     * @return The toast blueprint.
     */
    public static ToastBlueprint fetchToast(Identifier id) {
        return ToastsManager.INSTANCE.fetch(id);
    }

    /**
     * Fetch, restore and show a UIToast
     * @param id The identifier usually modid:filename
     * @return The toast.
     */
    @Environment(EnvType.CLIENT)
    public static UIToast openToast(Identifier id) {
        UIToast toast = ToastsManager.INSTANCE.fetch(id).restore();
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
