package Controllers;

import Entities.InstructionList;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

        // Prettify?
        buttonCloseSearch.setGraphic(GlyphsBuilder.create(FontAwesomeIconView.class).
                glyph(FontAwesomeIcon.CLOSE).
                build()
        );
        search.setGraphic(GlyphsBuilder.create(FontAwesomeIconView.class).
                glyph(FontAwesomeIcon.SEARCH).
                build()
        );
        buttonSearchUp.setGraphic(GlyphsBuilder.create(FontAwesomeIconView.class).
                glyph(FontAwesomeIcon.SEARCH_PLUS).
                build()
        );
        buttonSearchDown.setGraphic(GlyphsBuilder.create(FontAwesomeIconView.class).
                glyph(FontAwesomeIcon.SEARCH_MINUS).
                build()
        );
    }

    public Label search;
    public Button buttonCloseSearch;
    public TextField fieldSearch;
    public Button buttonSearchUp;
    public Button buttonSearchDown;
    public Label labelMatches;
    public ListView<MnemonicDescription> instructionList;
}
