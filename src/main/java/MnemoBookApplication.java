import elements.EditorPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import Controllers.MainSceneController;
import org.fxmisc.richtext.CodeArea;


import java.io.IOException;
import java.net.URL;

public class MnemoBookApplication extends Application {

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");
        loader.setLocation(xmlUrl);
        try {
             Parent root = loader.load();
             MainSceneController controller = loader.getController();
             EditorPane editorPane = (EditorPane) root.lookup("#editorPane");
             controller
                     .setStage(stage)
                     .setCodeEditor(editorPane.getCodeEditor())
                     .setCurrentFileName(editorPane.getCurrentFileName());
             stage.setScene(new Scene(root));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}