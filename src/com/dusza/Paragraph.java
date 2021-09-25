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
    private List<Tag> tagList = new ArrayList<>();

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
                    String url = fullText.substring(count+2, count2);
                    StringBuilder urlBuilder = new StringBuilder();

                    for(int j=0; j<url.length(); j++) {
                        if(url.charAt(j) == '"') urlBuilder.append("%22");
                        else urlBuilder.append(url.charAt(j));
                    }

                    Tag tag = new Tag(i, 1, Highlights.LINK, false);
                    tag.setUrl(urlBuilder.toString());
                    Tag tag2 = new Tag(count, count2-count+1, Highlights.LINK, true);
                    tagList.add(tag);
                    tagList.add(tag2);
                }
            }
            if(fullText.charAt(i) == HiGHLIGHT && fullText.charAt(Math.max(0, i-1)) != '\\') {
                if(i+1 < fullText.length() && fullText.charAt(i+1) == HiGHLIGHT) {
                    if(bold) {
                        Tag tag = new Tag(i, 2, Highlights.BOLD, true);
                        tagList.add(tag);
                        bold = false;
                    } else {
                        Tag tag = new Tag(i, 2, Highlights.BOLD, false);
                        tagList.add(tag);
                        bold = true;
                    }
                    i++;
                } else {
                    if(italic) {
                        Tag tag = new Tag(i, 1, Highlights.ITALIC, true);
                        tagList.add(tag);
                        italic = false;
                    } else {
                        Tag tag = new Tag(i, 1, Highlights.ITALIC, false);
                        tagList.add(tag);
                        italic = true;
                    }
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

    public String getHTML() {
        determineType();
        processText();
        return String.format("<%s>%s<%s>", type, finishedText, "/"+type);
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }
}
