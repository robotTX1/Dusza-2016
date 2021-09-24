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

    public String getCharacters() {
        StringBuilder builder = new StringBuilder();
        if(characters.length() % 2 != 0) characters = characters+" ";

        String key = null;
        String key2;

        for(int i=0; i<characters.length(); i++) {
            if(i + 2 <= characters.length()) key = characters.substring(i, i+2);
            else key = "";
            key2 = characters.substring(i,i+1);
            if(specialCharacters.containsKey(key)) {
                builder.append(specialCharacters.get(key));
                i++;
            } else if(specialCharacters.containsKey(key2)) {
                builder.append(specialCharacters.get(key2));
            } else {
                builder.append(characters.charAt(i));
            }
        }

        return builder.toString();

//        for(Map.Entry<String, String> entry : specialCharacters.entrySet()) {
//            characters = characters.replaceAll(entry.getKey(), entry.getValue());
//        }
//
//        return characters;
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
