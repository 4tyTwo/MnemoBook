package MnemoApp.Entities;

import MnemoApp.ApplicationConfig;
import MnemoApp.Utils.JSONConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstructionList {

    public static List<Instruction> getInstructions() {
        return INSTRUCTION_MAPPING.get(ApplicationConfig.getAssembler());
    }

    public static final List<Instruction> MIPS_INSTRUCTIONS = JSONConverter.convert(
        InstructionList.class.getResourceAsStream("/MIPS.json"),
        Instructions.class
    ).instructionList.stream().sorted().collect(Collectors.toList());

    public static final List<Instruction> X86_INSTRUCTIONS = JSONConverter.convert(
            InstructionList.class.getResourceAsStream("/x86.json"),
            Instructions.class
    ).instructionList.stream().sorted().collect(Collectors.toList());

    private static final Map<Assembler, List<Instruction>> INSTRUCTION_MAPPING = Map.of(
            Assembler.MIPS, MIPS_INSTRUCTIONS,
            Assembler.FASM, X86_INSTRUCTIONS
    );

    private static class Instructions {
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Instructions(@JsonProperty("instructions") List<Instruction> instructionList) {
            this.instructionList = instructionList;
        }

        private final List<Instruction> instructionList;
    }
}
