package com.gbujak.springcraigslistclone.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Component;
import org.commonmark.renderer.html.HtmlRenderer;

@Component
public class UserInputProcessor {
    private final Parser parser;
    private Node document;
    private final HtmlRenderer renderer;
    private final PolicyFactory sanitizer;

    public UserInputProcessor() {
        parser = Parser.builder().build();
        renderer = HtmlRenderer.builder().build();
        sanitizer = Sanitizers.FORMATTING
                .and(Sanitizers.BLOCKS)
                .and(Sanitizers.LINKS);
    }

    public String process(String input) {
        document = parser.parse(input);
        var html = renderer.render(document);
        return sanitizer.sanitize(html);
    }
}
