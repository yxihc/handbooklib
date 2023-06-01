package com.taopao.oldbase.base.bean;

/**
 * Created by blacKey on 2016/3/11.
 */
public class HighRiskFactor {
    String text;
    String header;

    public HighRiskFactor(String text, String header) {
        this.text = text;
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
