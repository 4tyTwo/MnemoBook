package MnemoBookApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.*;
import java.nio.file.Files;

public class MainSceneController {

    @FXML
    private Button mainButton;
    @FXML
    private CodeArea codeEditor;

    private Stage stage;

    private int timesClicked = 0;

    public void setStage(Stage stage) {
        if (this.stage == null)
            this.stage = stage;
    }

    public void buttonClicked() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        File file = chooser.showOpenDialog(stage);
        try {
            String contents = new String(Files.readAllBytes(file.toPath()));
            codeEditor.replaceText(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
