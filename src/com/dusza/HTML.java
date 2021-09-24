package com.dusza;

import java.util.ArrayList;
import java.util.List;

public class HTML {
    private List<Paragraph> paragraphs;

    public HTML(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public void ProcessdMD(List<Paragraph> input) {
        paragraphs = new ArrayList<>();

        for (Paragraph p: input) {
            boolean bold = false;
            boolean italic = false;
            boolean link = false;
            boolean paragraph = false;
            StringBuilder url = new StringBuilder();
            String[] s = p.getFullText().split(" ");

            StringBuilder charChainFragment = new StringBuilder();
            System.out.println(s);

            int i = 0;
            while (i < s.length)
            {
                charChainFragment.append(s[i]);
                if(s[i].substring(0,2).equals("**") && !bold) {
                    bold = true;
                    saveChange = true;

                } else if (s[i].charAt(0) == '*' && !italic) {
                    italic = true;
                    saveChange = true;
                } else if (s[i].charAt(0) == '[' && !link) {
                    link = true;
                    saveChange = true;
                }

                if(s[i].substring(s[i].length()-3, s[i].length()-1).equals("**") && bold) {
                    bold = false;
                    saveChange = true;

                } else if (s[i].charAt(s[i].length()-1) == '*' && italic) {
                    italic = false;
                    saveChange = true;
                } else if (s[i].charAt(s[i].length()-1) == ']' && link) {
                    link = false;
                    saveChange = true;
                    int index = i;
                    while (s[index].charAt(s[index].length() - 1) != ')') {
                        url.append(s[index]);
                        index++;
                    }
                    i+= i-index-1;
                }

                if (saveChange) {
                    p.addNewChainPiece(new CharacterChain(
                            charChainFragment.toString(),italic, bold, link, url.toString()
                            ));
                    charChainFragment = new StringBuilder();
                    saveChange = false;
                }
                i++;
            }
        }

    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
