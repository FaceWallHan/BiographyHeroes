package com.example.hero;

public class ChatBean {
    private boolean type;
    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatBean(boolean type, String content) {
        this.type = type;
        this.content = content;
    }

    private String content;
}
