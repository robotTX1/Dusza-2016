package com.dusza;

public class Tag {
    private final int index;
    private final int length;
    private final Highlights type;
    private final boolean end;
    private String url;

    public Tag(int index, int length, Highlights type, boolean end) {
        this.index = index;
        this.length = length;
        this.type = type;
        this.end = end;
    }

    public int getIndex() {
        return index;
    }

    // Visszaadja a megfelelő nyitó/záró taget
    public String getType() {
        if(end) return type.getTag().charAt(0) + "/" + type.getTag().substring(1);
        if(type == Highlights.LINK) return "<a href=\"" + url + "\">";
        return type.getTag();
    }

    public int getLength() {
        return length - 1;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
