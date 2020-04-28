package Controllers;

import Entities.InstructionList;
import elements.MnemonicDescription;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class HelpWindowController {

    public void setupElements() {
        List<MnemonicDescription> descriptions = InstructionList.INSTRUCTION_LIST.stream().
                map(MnemonicDescription::new).collect(Collectors.toList());
        instructionList.getItems().remove(0);
        instructionList.getItems().addAll(descriptions);
    }

    public Button buttonCloseSearch;
    public TextField fieldSearch;
    public Button buttonSearchUp;
    public Button buttonSearchDown;
    public Label labelMatches;
    public ListView<MnemonicDescription> instructionList;
}
