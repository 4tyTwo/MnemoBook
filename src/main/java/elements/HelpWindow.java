package elements;

import Controllers.HelpWindowController;
import Entities.InstructionList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class HelpWindow extends VBox {

    public HelpWindow() {
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
        List<MnemonicDescription> descriptions = InstructionList.INSTRUCTION_LIST.stream().
                map(MnemonicDescription::new).collect(Collectors.toList());
        cont.instructionList.getItems().remove(0);
        cont.instructionList.getItems().addAll(descriptions);
    }
}
