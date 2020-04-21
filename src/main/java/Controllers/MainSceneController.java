package Controllers;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.*;
import java.nio.file.Files;

public class MainSceneController {

    private CodeArea codeEditor;

    private File currentFile;

    public MainSceneController setCodeEditor(CodeArea codeEditor) {
        this.codeEditor = codeEditor;
        return this;
    }

    private Stage stage;

    public MainSceneController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void fileOpenButtonClicked() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        File file = chooser.showOpenDialog(stage);
        try {
            String contents = new String(Files.readAllBytes(file.toPath()));
            codeEditor.replaceText(contents);
            currentFile = file;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveFileButtonClicked() {
        if (currentFile == null) {
            saveFileAsButtonClicked();
        } else {
            saveTextToFile(codeEditor.getText(), currentFile);
        }
    }

    public void saveFileAsButtonClicked() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        File file = chooser.showSaveDialog(stage);
        if (file != null) {
            String text = codeEditor.getText();
            saveTextToFile(text, file);
        }
    }


    private void saveTextToFile(String content, File file) {
        currentFile = file;
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ignored) {

        }
    }
}
