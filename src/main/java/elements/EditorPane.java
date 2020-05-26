package elements;

import Controllers.EditorPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;

import java.io.IOException;
import java.net.URL;

public class EditorPane extends HBox {

    public CodeArea getCodeEditor() {
        return (CodeArea) this.lookup("#codeEditor");
    }

    public Label getCurrentFileName() {
        return (Label) this.lookup("#currentFileName");
    }

    public EditorPane() {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/editorPane.fxml");
        loader.setRoot(this);
        loader.setLocation(xmlUrl);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
            EditorPaneController controller = loader.getController();
            controller.setup(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
