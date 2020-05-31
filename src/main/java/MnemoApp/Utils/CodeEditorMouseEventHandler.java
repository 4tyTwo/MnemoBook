package MnemoApp.Utils;


import MnemoApp.Entities.Instruction;
import MnemoApp.Entities.InstructionList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.event.MouseOverTextEvent;

import java.util.Optional;

public class CodeEditorMouseEventHandler implements EventHandler<MouseOverTextEvent> {

    private Popup popup;

    private final static String popupStyle = "-fx-background-color: black; -fx-text-fill: white; -fx-padding: 5;";

    private final CodeArea codeEditor;

    public CodeEditorMouseEventHandler(CodeArea codeEditor) {
        this.codeEditor = codeEditor;
        popup = new Popup();
    }

    @Override
    public void handle(MouseOverTextEvent mouseOverTextEvent) {
        if (mouseOverTextEvent.getEventType().equals(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN)) {
            maybeCreatePopup(mouseOverTextEvent);
        }
        if (mouseOverTextEvent.getEventType().equals(MouseOverTextEvent.MOUSE_OVER_TEXT_END)) {
            popup.hide();
        }
    }

    private void maybeCreatePopup(MouseOverTextEvent event) {
        String text = getWordByCharacterPosition(event.getCharacterIndex());
        Optional<Instruction> matchedInstruction = InstructionList.INSTRUCTION_LIST
                .stream()
                .filter(instruction -> instruction.getName().toLowerCase().equals(text))
                .findFirst();
        if (matchedInstruction.isPresent()) {
            Label popupMsg = new Label();
            Point2D pos = event.getScreenPosition();
            popupMsg.setStyle(popupStyle);
            popupMsg.setText(matchedInstruction.get().getDescription());
            popup = new Popup();
            popup.getContent().add(popupMsg);
            popup.show(codeEditor, pos.getX(), pos.getY() + 10);
        }
    }

    private String getWordByCharacterPosition(int chId) {
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
