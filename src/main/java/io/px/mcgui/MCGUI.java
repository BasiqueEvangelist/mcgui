package io.px.mcgui;

import io.px.mcgui.mcui.DocumentManager;
import io.px.mcgui.mcui.ToastsManager;
import io.px.mcgui.mcui.elements.UIView;
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
     * Fetch a UIView.
     *
     * @param id The identifier, usually modid:filename
     * @return The UIView, can be shown using MCGUI.open(UIView view)
     */
    public static UIView fetchView(Identifier id) {
        return DocumentManager.INSTANCE.fetch(id);
    }

    /**
     * Open a UIView.
     *
     * @param view The UIView to open.
     */
    @Environment(EnvType.CLIENT)
    public static void openView(UIView view) {
        MinecraftClient.getInstance().openScreen(view);
    }

    /**
     * Open a UIView
     *
     * @param id The identifier, usually modid:filename
     * @return The UIView that was opened.
     */
    @Environment(EnvType.CLIENT)
    public static UIView openView(Identifier id) {
        UIView view = DocumentManager.INSTANCE.fetch(id);
        MinecraftClient.getInstance().openScreen(view);
        return view;
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
