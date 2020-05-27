package Controllers;

import Utils.SyntaxHighlighter;
import elements.EditorPane;
import javafx.scene.control.Label;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Optional;


public class EditorPaneController {

    public CodeArea codeEditor;
    public Label currentFileName;

    public void setup(EditorPane pane) {
        codeEditor.prefHeightProperty().bind(pane.heightProperty());
        codeEditor.prefWidthProperty().bind(pane.widthProperty());


        SyntaxHighlighter highlighter = new SyntaxHighlighter(codeEditor);
        codeEditor.setParagraphGraphicFactory(LineNumberFactory.get(codeEditor));
        Subscription cleanupWhenDone = codeEditor.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(highlighter::computeHighlightingAsync)
                .awaitLatest(codeEditor.multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(highlighter::applyHighlighting);
    }
}
