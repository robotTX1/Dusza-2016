package com.dusza;

public class CharacterChain {
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
        return characters;
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
