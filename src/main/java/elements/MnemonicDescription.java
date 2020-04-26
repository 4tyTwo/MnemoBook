package elements;

import Controllers.MnemonicDescriptionController;
import Entities.Mnemonic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    public MnemonicDescription(Mnemonic m) {
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
        List<String> mnems = m.getArguments();
        List<Text> textMnems = mnems.stream().map(Text::new).collect(Collectors.toList());
        cont.arguments.getItems().addAll(textMnems);
        cont.arguments.setMaxHeight(25 * textMnems.size());
    }
}
