package Controllers;

import elements.EditorPane;
import javafx.scene.control.Label;
import org.fxmisc.richtext.CodeArea;


public class EditorPaneController {

    public CodeArea codeEditor;
    public Label currentFileName;

    public void setup(EditorPane pane) {
        codeEditor.prefHeightProperty().bind(pane.heightProperty());
        codeEditor.prefWidthProperty().bind(pane.widthProperty());
    }
}
