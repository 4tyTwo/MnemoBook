package MnemoApp.Controllers;

import MnemoApp.Entities.Instruction;
import MnemoApp.Entities.InstructionList;
import MnemoApp.Utils.CodeEditorMouseEventHandler;
import MnemoApp.Utils.SyntaxHighlighter;
import MnemoApp.elements.EditorPane;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.stage.Popup;
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
        codeEditor.setMouseOverTextDelay(Duration.ofSeconds(1));
        codeEditor.addEventHandler(MouseOverTextEvent.ANY, new CodeEditorMouseEventHandler(codeEditor));
    }
}
