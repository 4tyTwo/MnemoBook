package elements;

import Controllers.HelpWindowController;
import Entities.Mnemonic;
import Entities.MnemonicsList;
import Utils.JSONConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class HelpWindow extends VBox {

    public HelpWindow() {
        System.out.println("HelpWindow constructor");
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/helpWindow.fxml");
        loader.setRoot(this);
        loader.setLocation(xmlUrl);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
            setDescriptions(loader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDescriptions(FXMLLoader loader) {
        HelpWindowController cont = loader.getController();
        MnemonicsList mnemonicsList = JSONConverter.convert(
                "/Users/I.toporkov/Documents/adapters/MnemoBook/src/main/resources/descriptions/mnemonics.json",
                MnemonicsList.class
        );
        List<MnemonicDescription> descriptions =
                mnemonicsList.getMnemonicList().stream().
                map(MnemonicDescription::new).collect(Collectors.toList());
        cont.mnemonicsList.getItems().remove(0);
        cont.mnemonicsList.getItems().addAll(descriptions);
    }
}
