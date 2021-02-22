package io.px.mcgui.mcui;

import io.px.mcgui.mcui.elements.UITemplate;
import io.px.mcgui.mcui.elements.UIView;
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

public enum TemplateManager implements SimpleSynchronousResourceReloadListener {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger("MCGui/DocumentManager");
    private final HashMap<Identifier, UITemplate> templates = new HashMap<>();

    @Override
    public void apply(ResourceManager manager) {
        for (Identifier rawid : manager.findResources("templates", p -> p.endsWith(".mcui"))) {
            Identifier id = new Identifier(rawid.getNamespace(), rawid.getPath().substring("templates/".length(), rawid.getPath().length() - ".mcui".length()));
            try {
                Resource res = manager.getResource(rawid);
                String baseUri = rawid.getNamespace() + "/" + "templates" + "/" + rawid.getPath();
                Document doc = Jsoup.parse(res.getInputStream(), "UTF-8", baseUri);
                templates.put(id, MCUIParser.parseTemplate(doc));
            }
            catch (IOException e) {
                LOGGER.error("Failed to load file {} due to exception: ", id, e);
            }
        }
    }

    public @Nullable UITemplate fetch(Identifier id) {
        return templates.get(id);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("mcgui", "templates");
    }
}
