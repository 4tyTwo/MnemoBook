package MnemoApp;
import MnemoApp.Entities.Assembler;

import java.util.prefs.Preferences;


public class ApplicationConfig {
    public static final Preferences PREFERENCES = Preferences.userRoot().node("MnemoBook");

    public static Assembler getAssembler() {
        String as = PREFERENCES.get("assembler", "MIPS");
        switch (as) {
            case "MIPS": return Assembler.MIPS;
            case "fasm": return Assembler.FASM;
        }
        return Assembler.MIPS;
    }
}
