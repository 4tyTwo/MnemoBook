package Controllers;

import elements.EditorPane;
import elements.HelpWindow;
import elements.MnemonicDescription;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

import java.io.*;
import java.nio.file.Files;

public class MainSceneController {

    public EditorPane editorPane;
    private CodeArea codeEditor;
    private Label currentFileName;

    private File currentFile;

    public MainSceneController setCodeEditor(CodeArea codeEditor) {
        this.codeEditor = codeEditor;
        return this;
    }

    public MainSceneController setCurrentFileName(Label currentFileName) {
        this.currentFileName = currentFileName;
        return this;
    }

    private Stage stage;

    public MainSceneController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void fileOpenButtonClicked() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Assembler code", "*.asm", "*.s"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        chooser.setTitle("Open Resource File");
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            try {
                String contents = new String(Files.readAllBytes(file.toPath()));
                codeEditor.replaceText(contents);
                currentFile = file;
                currentFileName.setText(file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void mnemonicsButtonClicked() {
        Group root = new Group();
        Stage stage = new Stage();
        stage.setTitle("Help");
        HelpWindow window = new HelpWindow();
        window.prefHeightProperty().bind(stage.heightProperty());
        window.prefWidthProperty().bind(stage.widthProperty());
        ListView<MnemonicDescription> lw = (ListView<MnemonicDescription>) window.lookup("#instructionList");
        lw.prefHeightProperty().bind(window.heightProperty().subtract(70));
        stage.setMinWidth(600.0);
        stage.setMinHeight(400.0);
        root.getChildren().add(window);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
