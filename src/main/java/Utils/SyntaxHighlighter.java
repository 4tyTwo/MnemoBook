package Utils;

import Entities.Instruction;
import Entities.InstructionList;
import javafx.concurrent.Task;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SyntaxHighlighter {
    private static final List<String> INSTRUCTIONS = InstructionList.INSTRUCTION_LIST
            .stream()
            .map(Instruction::getName)
            .map(String::toLowerCase)
            .collect(Collectors.toList());

    private static final String INSTRUCTIONS_PATTERN = "\\b(" + String.join("|", INSTRUCTIONS) + ")\\b";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<INSTRUCTION>" + INSTRUCTIONS_PATTERN + ")"
    );

    private final ExecutorService executor = Executors.newSingleThreadExecutor();;
    private final CodeArea codeArea;

    public SyntaxHighlighter(CodeArea codeArea) {
        this.codeArea = codeArea;
    }

    public Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {

        String text = codeArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<>() {
            @Override
            protected StyleSpans<Collection<String>> call() {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    public void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("INSTRUCTION") != null ? "instruction" : null;
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

}
