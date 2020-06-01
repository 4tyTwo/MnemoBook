package MnemoApp.Highlighting;

import javafx.concurrent.Task;
import org.fxmisc.richtext.model.StyleSpans;

import java.util.Collection;

public interface SyntaxHighlighter {

    public Task<StyleSpans<Collection<String>>> computeHighlighting(String text);
}
