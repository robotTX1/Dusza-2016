package com.dusza;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private String type;
    private String fullText;
    private List<CharacterChain> characterChains = new ArrayList<>();

    private void setType() {
        int count = 0;
        for(int i=0; i<6; i++) {
            if(fullText.charAt(i) == '#') count++;
            else break;
        }

        if(count > 0) {
            type = "h" + count;
        } else {
            this.type = "p";
        }
    }

    public String getOutputHTML() {
        return "";
    }

    public boolean addNewChainPiece(CharacterChain chain) {
        return characterChains.add(chain);
    }

    public void setCharacterChainList(List<CharacterChain> newList) {
        this.characterChains = newList;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getFullText() {
        return fullText;
    }
}
