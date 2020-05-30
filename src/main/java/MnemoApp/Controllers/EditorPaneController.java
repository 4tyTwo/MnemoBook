package MnemoApp.Controllers;

import MnemoApp.Entities.Instruction;
import MnemoApp.Entities.InstructionList;
import MnemoApp.Utils.SyntaxHighlighter;
import MnemoApp.elements.EditorPane;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.event.MouseOverTextEvent;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Currency;
import java.util.Optional;
import java.util.stream.Stream;


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
        Popup popup = new Popup();
        codeEditor.addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN, e -> {
            int chIdx = e.getCharacterIndex();
            String text = getCurrentWord(chIdx);
            Optional<Instruction> matchedInstruction = InstructionList.INSTRUCTION_LIST
                    .stream()
                    .filter(instruction -> instruction.getName().toLowerCase().equals(text))
                    .findFirst();
            if (matchedInstruction.isPresent()) {
                Label popupMsg = new Label();
                Point2D pos = e.getScreenPosition();
                popupMsg.setStyle(
                        "-fx-background-color: black;" +
                                "-fx-text-fill: white;" +
                                "-fx-padding: 5;");
                popupMsg.setText(matchedInstruction.get().getDescription());
                popup.getContent().clear();
                popup.getContent().add(popupMsg);
                popup.show(codeEditor, pos.getX(), pos.getY() + 10);
            }
        });
        codeEditor.addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_END, e -> {
            popup.hide();
        });
    }

    private String getCurrentWord(int chId) {
        int begin = chId;
        String tmp;
        for (; begin >= 0; --begin) {
            tmp = codeEditor.getText(begin, begin + 1);
            if (tmp.equals("") || tmp.equals("\n") || tmp.equals("\t")) {
                break;
            }
        }
        int end = chId;
        for (; end >= 0; ++end) {
            tmp = codeEditor.getText(end, end + 1);
            if (tmp.equals("\n")|| tmp.equals(" ") || tmp.equals("\t") || tmp.equals(",")) {
                break;
            }
        }
        return codeEditor.getText(begin+1, end);
    }
}
