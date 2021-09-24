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
            String url = "";
            String[] s = p.getFullText().split(" ");

            System.out.println(s);

            int i = 0;
            while (i < s.length)
            {
                if(s[i].substring(0,2) == "**" && !bold) {
                    bold = true;

                } else if (s[i].charAt(0) == '*' && !italic) {
                    italic = true;
                } else if (s[i].charAt(0) == '[' && !link)
                    link = true;
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
