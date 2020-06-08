package MnemoApp.Controllers;

import MnemoApp.ApplicationConfig;
import MnemoApp.elements.EditorPane;
import MnemoApp.elements.HelpWindow;
import MnemoApp.elements.MnemonicDescription;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;

public class MainSceneController {

    public EditorPane editorPane;
    private CodeArea codeEditor;
    private Label currentFileName;

    private File currentFile;
    private String lastSavedText = "";

    public MainSceneController setCodeEditor(CodeArea codeEditor) {
        this.codeEditor = codeEditor;
        codeEditor.addEventHandler(KeyEvent.KEY_PRESSED, KE -> {
            String currentText = codeEditor.getText();
            boolean isSaved = currentText.equals(lastSavedText);
            setFileSavedIndicator(isSaved);
        });
        return this;
    }

    private void setFileSavedIndicator(boolean isSaved) {
        String filename = currentFileName.getText();
        String newfilename = filename;
        boolean currentIndicator = !filename.startsWith("* ");
        if (currentIndicator != isSaved) {
            if (isSaved) {
                newfilename = filename.substring(2);
            } else {
                newfilename = "* " + filename;
            }
        }
        currentFileName.setText(newfilename);
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
                lastSavedText = codeEditor.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFileButtonClicked() {
        if (currentFile == null) {
            saveFileAsButtonClicked();
        } else {
            String content = codeEditor.getText();
            saveTextToFile(content, currentFile);
            lastSavedText = content;
        }
    }

    public void saveFileAsButtonClicked() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        File file = chooser.showSaveDialog(stage);
        if (file != null) {
            String content = codeEditor.getText();
            saveTextToFile(content, file);
            lastSavedText = content;
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

    public void mipsAssemblyChosen(ActionEvent actionEvent) {
        ApplicationConfig.PREFERENCES.put("assembler", "MIPS");
        clearStyleSpans();
    }

    public void fasmAssemblyChosen(ActionEvent actionEvent) {
        ApplicationConfig.PREFERENCES.put("assembler", "fasm");
        clearStyleSpans();
    }

    private void clearStyleSpans() {
        codeEditor.setStyleSpans(0,
                new StyleSpansBuilder<Collection<String>>()
                        .add(Collections.emptyList(), codeEditor.getText().length())
                        .create()
        );
    }

    public void exitButtonClicked(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void newFileButtonClicked(ActionEvent actionEvent) {
    }
}
