package elements;

import Controllers.HelpWindowController;
import Entities.InstructionList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class HelpWindow extends VBox {

    public HelpWindow() {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/helpWindow.fxml");
        loader.setRoot(this);
        loader.setLocation(xmlUrl);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
            HelpWindowController controller = loader.getController();
            controller.setupElements();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
