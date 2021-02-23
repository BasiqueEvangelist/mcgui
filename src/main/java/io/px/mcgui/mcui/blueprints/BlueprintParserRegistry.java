package io.px.mcgui.mcui.blueprints;

import io.px.mcgui.mcui.templating.InsertTemplateBlueprint;

import java.util.HashMap;
import java.util.Map;

public final class BlueprintParserRegistry {
    private static final Map<String, BlueprintParser<?>> MAP = new HashMap<>();

    private BlueprintParserRegistry() {

    }

    public static <T extends BlueprintParser<?>> T register(String name, T parser) {
        MAP.put(name, parser);
        return parser;
    }

    public static BlueprintParser<?> get(String name) {
        return MAP.get(name);
    }

    public static final BlueprintParser<ButtonBlueprint> BUTTON = register("button", ButtonBlueprint::parse);
    public static final BlueprintParser<LabelBlueprint> LABEL = register("label", LabelBlueprint::parse);
    public static final BlueprintParser<SeparatorBlueprint> SEPARATOR = register("separator", SeparatorBlueprint::parse);
    public static final BlueprintParser<CheckboxBlueprint> CHECKBOX = register("checkbox", CheckboxBlueprint::parse);
    public static final BlueprintParser<InsertTemplateBlueprint<?>> TEMPLATE = register("template", InsertTemplateBlueprint::parse);
}
