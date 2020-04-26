package elements;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.fxmisc.richtext.CodeArea;

import java.io.IOException;
import java.net.URL;

public class EditorPane extends BorderPane {

    @FXML
    CodeArea codeEditor;

    public CodeArea getCodeEditor() {
        return codeEditor;
    }

    public EditorPane() {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/editorPane.fxml");
        loader.setRoot(this);
        loader.setController(this);
        loader.setLocation(xmlUrl);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
