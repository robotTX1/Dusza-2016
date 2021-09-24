package com.dusza;

import java.util.ArrayList;
import java.util.List;

public class HTML {
    private List<Paragraph> paragraphs;

    public HTML(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public void ProcessdMD() {

        for (int paragIndex = 0; paragIndex < paragraphs.size(); paragIndex++) {
            Paragraph p = paragraphs.get(paragIndex);
            boolean saveChange = false;
            boolean bold = false;
            boolean italic = false;
            boolean link = false;
            boolean paragraph = false;
            String url = "";
            String[] s = p.getFullText().split(" ");


            StringBuilder charChainFragment = new StringBuilder();

            int i = 0;
            while (i < s.length)
            {
                charChainFragment.append(" ").append(s[i]);
                if(s[i].startsWith("**") && !bold) {
                    bold = true;
                    saveChange = true;

                } else if (s[i].charAt(0) == '*' && !italic) {
                    italic = true;
                    saveChange = true;
                } else if (s[i].charAt(0) == '[' && !link) {
                    link = true;
                    saveChange = true;
                }

                if((s[i].length()>2 && s[i].substring(s[i].length()-3, s[i].length()-1).equals("**")) || (s[i+1].length()>2 && s[i+1].substring(s[i+1].length()-3, s[i+1].length()-1).equals("**")) && bold) {
                    bold = false;
                    saveChange = true;

                } else if ((s[i].charAt(s[i].length()-1) == '*') || (s[i+1].charAt(s[i+1].length()-1) == '*') && italic) {
                    italic = false;
                    saveChange = true;
                } else if ((s[i].charAt(s[i].length()-1) == ']') || (s[i+1].charAt(s[i+1].length()-1) == ']') && link) {
                    link = false;
                    saveChange = true;


                    int index = s[i].indexOf("]");
                    url = url.replaceAll("\"", "%22");
                }

                if (saveChange) {
                    paragraphs.get(paragIndex).addNewChainPiece(new CharacterChain(
                            charChainFragment.toString(),italic, bold, link, url
                            ));
                    charChainFragment = new StringBuilder();
                    saveChange = false;
                }
                i++;
            }

            paragraphs.get(paragIndex).addNewChainPiece(new CharacterChain(
                    charChainFragment.toString(),italic, bold, link, url
            ));
        }


    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
