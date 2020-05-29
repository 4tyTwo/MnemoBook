package MnemoApp.elements;

import MnemoApp.Controllers.MnemonicDescriptionController;
import MnemoApp.Entities.Instruction;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MnemonicDescription extends VBox {

    public MnemonicDescription() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mnemonicDescription.fxml"));
        loader.setRoot(this);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MnemonicDescription(Instruction instruction) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mnemonicDescription.fxml"));
        loader.setRoot(this);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
            MnemonicDescriptionController controller = loader.getController();
            controller.setUpChildren(instruction);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
