package io.px.mcgui.mcui;

import io.px.mcgui.mcui.parsers.*;

import java.util.HashMap;
import java.util.Map;

public final class ElementParserRegistry {
    private static final Map<String, Parser<?>> MAP = new HashMap<>();

    private ElementParserRegistry() {

    }

    public static <T extends Parser<?>> T register(String name, T parser) {
        MAP.put(name, parser);
        return parser;
    }

    public static Parser<?> get(String name) {
        return MAP.get(name);
    }

    public static final ButtonParser BUTTON = register("button", new ButtonParser());
    public static final LabelParser LABEL = register("label", new LabelParser());
    public static final SeparatorParser SEPARATOR = register("separator", new SeparatorParser());
    public static final CheckboxParser CHECKBOX = register("checkbox", new CheckboxParser());
    public static final TemplateParser TEMPLATE = register("template", new TemplateParser());
}
