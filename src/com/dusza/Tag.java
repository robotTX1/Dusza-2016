package com.dusza;

public class Tag {
    private int index;
    private int length;
    private Highlights type;
    private boolean end;
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

    public void setIndex(int index) {
        this.index = index;
    }

    public String getType() {
        if(end) return type.getTag().substring(0,1) + "/" + type.getTag().substring(1);
        if(type == Highlights.LINK) return "<a href=\"" + url + "\">";
        return type.getTag();
    }

    public void setType(Highlights type) {
        this.type = type;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getLength() {
        return length - 1;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
