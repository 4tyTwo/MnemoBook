package MnemoApp.Highlighting;

import MnemoApp.ApplicationConfig;
import org.fxmisc.richtext.CodeArea;

import java.time.Duration;
import java.util.Optional;

public class SyntaxHighlightingManager {

    private static final SyntaxHighlighter MIPS_HIGHLIGHTING = new MipsSyntaxHighlighter();
    private static final SyntaxHighlighter FASM_HIGHLIGHTING = new FasmSynatHighlighter();

    private static SyntaxHighlighter getHighlighter() {
        switch (ApplicationConfig.getAssembler()) {
            case FASM: return FASM_HIGHLIGHTING;
            default: return MIPS_HIGHLIGHTING;
        }
    }

    public static void addHighlightingSupport(CodeArea codeArea) {
        codeArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(() -> getHighlighter().computeHighlighting(codeArea.getText()))
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t -> {
                    if(t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(highlighting -> {
                    codeArea.setStyleSpans(0, highlighting);
                });
    }
}
