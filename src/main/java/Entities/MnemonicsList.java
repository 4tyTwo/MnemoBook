package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MnemonicsList {

    @JsonProperty("mnemonics")
    private List<Mnemonic> mnemonicList;

    public List<Mnemonic> getMnemonicList() {
        return mnemonicList;
    }

    public MnemonicsList setMnemonicList(List<Mnemonic> mnemonicList) {
        this.mnemonicList = mnemonicList;
        return this;
    }

    @Override
    public String toString() {
        return "MnemonicsList{" +
                "mnemonicList=" + mnemonicList +
                '}';
    }
}
