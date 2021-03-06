package io.px.mcgui;

import io.px.mcgui.mcui.ViewScreenManager;
import io.px.mcgui.mcui.TemplateManager;
import io.px.mcgui.mcui.ToastsManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class MCGuiMain implements ModInitializer {
    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(TemplateManager.INSTANCE);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(ViewScreenManager.INSTANCE);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(ToastsManager.INSTANCE);
    }
}
