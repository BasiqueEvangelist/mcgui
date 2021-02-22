package io.px.mcgui;

import io.px.mcgui.mcui.DocumentManager;
import io.px.mcgui.mcui.TemplateManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class MCGuiMain implements ModInitializer {
    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(TemplateManager.INSTANCE);
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(DocumentManager.INSTANCE);
    }
}
