package MnemoApp.Controllers;

import MnemoApp.Entities.Instruction;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;

public class MnemonicDescriptionController {
    public Text description;
    public ListView<Text> arguments;
    public Label name;
    public Text example;
    public Text operation;
    public Label fullName;
    public ListView<Text> machineCodes;
    public VBox descriptionRoot;

    public void setUpChildren(Instruction instruction) {
        name.setText(instruction.getName());
        description.setText(instruction.getDescription());
        example.setText(instruction.getExample());
        operation.setText(instruction.getOperation());
        fullName.setText(instruction.getFullName());
        List<Text> Arguments = instruction.getArguments().stream().map(Text::new)
                .collect(Collectors.toList());
        arguments.getItems().addAll(Arguments);
        arguments.setMaxHeight(26 * Arguments.size());
        if (instruction.getMachineCodes() != null) {
            List<Text> codes = instruction.getMachineCodes().stream().map(Text::new)
                    .collect(Collectors.toList());
            machineCodes.getItems().addAll(codes);
            machineCodes.setMaxHeight(26 * codes.size());
        } else {
            descriptionRoot.getChildren().remove(machineCodes.getParent());
        }
    }
}
