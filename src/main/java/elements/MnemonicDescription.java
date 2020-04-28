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

    public MnemonicDescription(Instruction m) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mnemonicDescription.fxml"));
        loader.setRoot(this);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MnemonicDescriptionController cont = loader.getController();
        cont.name.setText(m.getName());
        cont.description.setText(m.getDescription());
        cont.example.setText(m.getExample());
        cont.operation.setText(m.getOperation());
        cont.fullName.setText(m.getFullName());
        List<Argument> mnems = m.getArguments();
        List<Text> textMnems = mnems.stream().map(Argument::toString).map(Text::new).collect(Collectors.toList());
        cont.arguments.getItems().addAll(textMnems);
        cont.arguments.setMaxHeight(26 * textMnems.size());
    }
}
