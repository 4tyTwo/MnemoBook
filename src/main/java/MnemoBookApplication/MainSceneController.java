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
    private CodeArea codeEditor;

    private Stage stage;

    public void setStage(Stage stage) {
        if (this.stage == null)
            this.stage = stage;
    }

    public void fileOpenButtonClicked() {
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

    public void saveFileButtonClicked() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        File file = chooser.showSaveDialog(stage);
        if (file != null) {
            String text = codeEditor.getText();
            saveTextToFile(text, file);
        }
    }


    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ignored) {

        }
    }
}
