package elements;

import Controllers.MnemonicDescriptionController;
import Entities.Argument;
import Entities.Instruction;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
