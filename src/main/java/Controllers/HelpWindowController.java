package Controllers;

import Entities.Instruction;
import Entities.InstructionList;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import elements.MnemonicDescription;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.*;
import java.util.stream.Collectors;

public class HelpWindowController {

    public Label search;
    public Button buttonCloseSearch;
    public TextField fieldSearch;
    public Button buttonSearchUp;
    public Button buttonSearchDown;
    public Label labelMatches;
    public ListView<MnemonicDescription> instructionList;

    private static final List<MnemonicDescription> descriptions =
            convertInstructionList(InstructionList.INSTRUCTION_LIST);

    public void setupElements() {
        instructionList.getItems().clear();
        instructionList.getItems().addAll(descriptions);

        buttonCloseSearch.setGraphic(createFontAwesomeIcon(FontAwesomeIcon.CLOSE));
        search           .setGraphic(createFontAwesomeIcon(FontAwesomeIcon.SEARCH));
        buttonSearchUp   .setGraphic(createFontAwesomeIcon(FontAwesomeIcon.SEARCH_PLUS));
        buttonSearchDown .setGraphic(createFontAwesomeIcon(FontAwesomeIcon.SEARCH_MINUS));

        fieldSearch.textProperty().addListener(this::search);
    }

    private void search(Observable observable, String oldValue, String newValue) {
        instructionList.getItems().clear();
        var inst = executeSearch(newValue);
        instructionList.getItems().addAll(
                convertInstructionList(inst)
        );
    }

    private Collection<Instruction> executeSearch(String query) {
        if (query == null || query.equals("")) {
            return InstructionList.INSTRUCTION_LIST;
        }
        return searchInstructions(query.toLowerCase());
    }

    private Collection<Instruction> searchInstructions(String query) {
        return InstructionList.INSTRUCTION_LIST
               .stream()
               .filter(instruction -> matches(instruction, query))
               .collect(Collectors.toCollection(TreeSet::new));
    }

    private boolean matches(Instruction instruction, String query) {
        String name = instruction.getName().toLowerCase();
        String description = instruction.getDescription().toLowerCase();
        return name.contains(query) || description.startsWith(query);
    }

    private Node createFontAwesomeIcon(FontAwesomeIcon icon) {
        return GlyphsBuilder
                .create(FontAwesomeIconView.class)
                .glyph(icon)
                .build();
    }

    private static List<MnemonicDescription> convertInstructionList(Collection<Instruction> instructions) {
        return instructions
                .stream()
                .map(MnemonicDescription::new)
                .collect(Collectors.toList());
    }
}
