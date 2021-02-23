/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */
package io.px.mcgui.mcui;

import io.px.mcgui.mcui.blueprints.ViewScreenBlueprint;
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

public enum ViewScreenManager implements SimpleSynchronousResourceReloadListener {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger("MCGui/ViewScreenManager");
    private final HashMap<Identifier, ViewScreenBlueprint<?>> screens = new HashMap<>();

    @Override
    public void apply(ResourceManager manager) {
        for (Identifier rawid : manager.findResources("screens", p -> p.endsWith(".mcui"))) {
            Identifier id = new Identifier(rawid.getNamespace(), rawid.getPath().substring("screens/".length(), rawid.getPath().length() - ".mcui".length()));
            try {
                Resource res = manager.getResource(rawid);
                String baseUri = rawid.getNamespace() + "/" + rawid.getPath();
                Document doc = Jsoup.parse(res.getInputStream(), "UTF-8", baseUri);
                screens.put(id, MCUIParser.parseScreen(doc));
            } catch (IOException e) {
                LOGGER.error("Failed to load file {} due to exception: ", id, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <C> @Nullable ViewScreenBlueprint<C> fetch(Identifier id) {
        return (ViewScreenBlueprint<C>) screens.get(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("mcgui", "documents");
    }
}
