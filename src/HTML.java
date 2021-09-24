import com.dusza.Paragraph;

import java.util.*;

public class HTML {


    private List<Paragraph> paragraphs;


    public HTML(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }


    public void ProcessdMD(List<Paragraph> input) {
        paragraphs = new ArrayList<>();
        boolean bold = false;
        boolean italic = false;
        boolean link = false;
        boolean paragraph = false;
        String url = "";

        for (Paragraph p: input) {

        }
        int i = 0;
        while (i < input.size())
        {
            String s = input.get(i);

            if (s.substring(0,1).equals("#"))
            {
                long headerCount = s.chars().count();

            } else if(s.equals("*")) {

            }

            i++;
        }
        for (String s: input) {

            switch (s)
            {
                case "#":
                    break;

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
