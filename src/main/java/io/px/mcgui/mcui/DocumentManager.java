/*
 * MCGui is licensed under the Mozilla Public License 2.0
 * Failure to follow this license will result in further action.
 */
package io.px.mcgui.mcui;

import io.px.mcgui.mcui.elements.UIDocument;
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

public enum DocumentManager implements SimpleSynchronousResourceReloadListener {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger("MCGui/DocumentManager");
    private final HashMap<Identifier, UIDocument> views = new HashMap<>();

    @Override
    public void apply(ResourceManager manager) {
        for (Identifier rawid : manager.findResources("documents", p -> p.endsWith(".mcui"))) {
            Identifier id = new Identifier(rawid.getNamespace(), rawid.getPath().substring("documents/".length(), rawid.getPath().length() - ".mcui".length()));
            try {
                Resource res = manager.getResource(rawid);
                String baseUri = rawid.getNamespace() + "/" + "documents" + "/" + rawid.getPath();
                Document doc = Jsoup.parse(res.getInputStream(), "UTF-8", baseUri);
                views.put(id, MCUIParser.parse(doc));
            }
            catch (IOException e) {
                LOGGER.error("Failed to load file {} due to exception: ", id, e);
            }
        }
    }

    public @Nullable UIDocument fetch(Identifier id) {
        return views.get(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("mcgui", "documents");
    }
}
