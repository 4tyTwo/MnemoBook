import Entities.MnemonicsList;
import Utils.JSONConverter;
import elements.EditorPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import Controllers.MainSceneController;


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
             EditorPane editorPane = new EditorPane();
             BorderPane bp = (BorderPane) root.lookup("#mainBox");
             bp.setCenter(editorPane);
             controller.setStage(stage).setCodeEditor(editorPane.getCodeEditor());
             stage.setScene(new Scene(root));
        }
        catch (IOException e) {
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}