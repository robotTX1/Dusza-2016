package com.dusza;

import java.util.HashMap;
import java.util.Map;

public class CharacterChain {
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

    private boolean italic;
    private boolean bold;
    private boolean link;
    private String characters;
    private String url;

    public CharacterChain(String characters, boolean italic, boolean bold, boolean link, String url) {
        this.italic=italic;
        this.bold=bold;
        this.link=link;
        this.url=url;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public static String getCharacters(String characters) {
        String tempString = characters;
        tempString = tempString.length() % 2 == 0 ? tempString : tempString+" ";
        String key;
        for(int i=0; i<tempString.length(); i++) {
             key = tempString.substring(i, i+2);
            if(specialCharacters.containsKey(key)) {
                System.out.println(tempString.substring(i+2));
                tempString = specialCharacters.get(key) + tempString.substring(i+2);
            }
        }

        return tempString;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
