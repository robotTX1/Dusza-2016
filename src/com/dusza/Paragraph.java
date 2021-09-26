package com.dusza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paragraph {
    private static final Map<String, String> specialCharacters = new HashMap<>();

    static {
        specialCharacters.put("\\*", "*");
        specialCharacters.put("\\(", "(");
        specialCharacters.put("\\)", ")");
        specialCharacters.put("\\[", "[");
        specialCharacters.put("\\]", "]");
        specialCharacters.put("\\\\", "\\");
        specialCharacters.put("<", "&lt;");
        specialCharacters.put(">", "&gt;");
    }

    private static final char HiGHLIGHT = '*';
    private static final char LINK_START = '[';

    private String type;
    private String fullText;
    private String finishedText;
    private final List<Tag> tagList = new ArrayList<>();

    // Ez a függvény eldönti a bekezdésről, hogy milyen típusú (p vagy hx)
    public void determineType() {
        int count = 0;
        for(int i=0; i<6; i++) {
            if(fullText.charAt(i) == '#') count++;
            else break;
        }

        fullText = fullText.substring(count).strip();

        if(count > 0) {
            type = "h" + count;
        } else {
            this.type = "p";
        }
    }

    // Ez a függvény feldolgozza a HTML jelöléseket úgy, hogy ne vegye figyelembe a speciális karaktereket.
    // Jelölések amiket feldolgoz: döntött szöveg (italic), félköver szöveg (bold), hivatkozás
    private void processText() {
        StringBuilder builder = new StringBuilder();

        boolean italic = false;
        boolean bold = false;

        for(int i=0; i<fullText.length(); i++) {
            if(fullText.charAt(i) == LINK_START && fullText.charAt(Math.max(0, i-1)) != '\\') {
                int count = i;

                while(count < fullText.length() && fullText.charAt(count) != ']') {
                    count++;
                }

                if(fullText.charAt(count+1) == '(') {
                    int count2 = count;
                    while(count2 < fullText.length() && fullText.charAt(count2) != ')') {
                        count2++;
                    }
                    String url = fullText.substring(count+2, count2).replaceAll("\"", "%22");

                    Tag tag = new Tag(i, 1, Highlights.LINK, false);
                    tag.setUrl(url);
                    Tag tag2 = new Tag(count, count2-count+1, Highlights.LINK, true);
                    tagList.add(tag);
                    tagList.add(tag2);
                }
            }
            if(fullText.charAt(i) == HiGHLIGHT && fullText.charAt(Math.max(0, i-1)) != '\\') {
                if(i+1 < fullText.length() && fullText.charAt(i+1) == HiGHLIGHT) {
                    Tag tag = new Tag(i, 2, Highlights.BOLD, bold);
                    tagList.add(tag);
                    bold = !bold;
                    i++;
                } else {
                    Tag tag = new Tag(i, 1, Highlights.ITALIC, italic);
                    tagList.add(tag);
                    italic = !italic;
                }
            }
        }

        if(italic) {
            Tag tag = new Tag(fullText.length()-1, 1, Highlights.ITALIC, true);
            tagList.add(tag);
        }
        if(bold) {
            Tag tag = new Tag(fullText.length()-1, 1, Highlights.BOLD, true);
            tagList.add(tag);
        }


        boolean found;
        String tmp =fixMarkdownSpecialCharacters();
        int diff = tmp.length() - fullText.length();
        for(int i=0; i<tmp.length(); i++) {
            found = false;
            for(Tag t : tagList) {
                if(t.getIndex() + diff == i) {
                    builder.append(t.getType());
                    i += t.getLength();
                    found = true;
                }
            }
            if(!found) builder.append(tmp.charAt(i));
        }

        finishedText = builder.toString();
    }


    // A függvény feladata az, hogy minden speciális karaktert átalakítson HTML kompatibilis karakterreké úgy,
    // hogy a HTML kóddá alakításhoz szükséges jelöléseket ne írja felül.
    private String fixMarkdownSpecialCharacters() {
        StringBuilder builder = new StringBuilder();
        if(fullText.length() % 2 != 0) fullText = fullText +" ";

        String key;
        String key2;

        for(int i = 0; i< fullText.length(); i++) {
            if(i + 2 <= fullText.length()) key = fullText.substring(i, i+2);
            else key = "";
            key2 = fullText.substring(i,i+1);
            if(specialCharacters.containsKey(key)) {
                builder.append(specialCharacters.get(key));
                i++;
            } else if(specialCharacters.containsKey(key2)) {
                builder.append(specialCharacters.get(key2));
            } else {
                builder.append(fullText.charAt(i));
            }
        }

        return builder.toString();
    }

    // Ez a függvény vissza adja a bekezdés HTML kódját.
    public String getHTML() {
        determineType();
        processText();
        return String.format("<%s>%s<%s>", type, finishedText, "/"+type);
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }
}
