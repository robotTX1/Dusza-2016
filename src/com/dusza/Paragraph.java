package com.dusza;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private String type;
    private List<CharacterChain> characterChains = new ArrayList<>();

    public String getOutputHTML() {
        return "";
    }

    public boolean addNewChainPiece(CharacterChain chain) {
        return characterChains.add(chain);
    }

    public void setCharacterChainList(List<CharacterChain> newList) {
        this.characterChains = newList;
    }
}
