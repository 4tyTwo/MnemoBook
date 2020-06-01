package MnemoApp.Controllers;

import MnemoApp.Highlighting.SyntaxHighlightingManager;
import MnemoApp.Utils.CodeEditorMouseEventHandler;
import MnemoApp.elements.EditorPane;
import javafx.scene.control.Label;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.event.MouseOverTextEvent;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Optional;


public class EditorPaneController {

    public CodeArea codeEditor;
    public Label currentFileName;

    public void setup(EditorPane pane) {
        codeEditor.prefHeightProperty().bind(pane.heightProperty());
        codeEditor.prefWidthProperty().bind(pane.widthProperty());

        SyntaxHighlightingManager.addHighlightingSupport(codeEditor);

        codeEditor.setMouseOverTextDelay(Duration.ofSeconds(1));
        codeEditor.addEventHandler(MouseOverTextEvent.ANY, new CodeEditorMouseEventHandler(codeEditor));
    }
}
