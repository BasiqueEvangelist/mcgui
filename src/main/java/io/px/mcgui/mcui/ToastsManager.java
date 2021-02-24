package io.px.mcgui.mcui;

import io.px.mcgui.mcui.toasts.ToastBlueprint;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public enum ToastsManager implements SimpleSynchronousResourceReloadListener {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger("MCGui/ToastsManager");
    private final HashMap<Identifier, ToastBlueprint> toasts = new HashMap<>();

    @Override
    public void apply(ResourceManager manager) {
        for (Identifier rawid : manager.findResources("toasts", p -> p.endsWith(".mcui"))) {
            Identifier id = new Identifier(rawid.getNamespace(), rawid.getPath().substring("toasts/".length(), rawid.getPath().length() - ".mcui".length()));
            try {
                Resource res = manager.getResource(rawid);
                String baseUri = rawid.getNamespace() + "/" + "toasts" + "/" + rawid.getPath();
                Document doc = Jsoup.parse(res.getInputStream(), "UTF-8", baseUri);
                toasts.put(id, MCUIParser.parseToast(doc));
            } catch (IOException e) {
                LOGGER.error("Failed to load file {} due to exception: ", id, e);
            }
        }
    }

    public @Nullable ToastBlueprint fetch(Identifier id) {
        return toasts.get(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("mcgui", "toasts");
    }
}
