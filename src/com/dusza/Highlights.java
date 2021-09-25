package com.dusza;

public enum Highlights {
    ITALIC("<em>"),
    BOLD("<strong>"),
    LINK("<a>");

    private String tag;

    public String getTag() {
        return tag;
    }

    Highlights(String tag) {
        this.tag = tag;
    }
}
