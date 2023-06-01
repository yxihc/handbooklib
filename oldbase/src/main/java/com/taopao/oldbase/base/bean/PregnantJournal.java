package com.taopao.oldbase.base.bean;

import java.io.Serializable;

/**
 * Created by blacKey on 2015/12/14.
 */
public class PregnantJournal implements Serializable {
    String id;
    String strJournalImage;
    String strTitle;
    String strJournalContent;
    String strCreateTime;
    String strUpdateTime;
    String journalType;
    String userIdNo;
    String url;

    public PregnantJournal(String id, String strJournalImage, String strTitle, String strJournalContent, String strCreateTime, String strUpdateTime, String journalType, String userIdNo, String url) {
        this.id = id;
        this.strJournalImage = strJournalImage;
        this.strTitle = strTitle;
        this.strJournalContent = strJournalContent;
        this.strCreateTime = strCreateTime;
        this.strUpdateTime = strUpdateTime;
        this.journalType = journalType;
        this.userIdNo = userIdNo;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public String getStrJournalImage() {
        return strJournalImage;
    }

    public String getStrJournalContent() {
        return strJournalContent;
    }

    public String getStrCreateTime() {
        return strCreateTime;
    }

    public String getJournalType() {
        return journalType;
    }

    public String getStrUpdateTime() {
        return strUpdateTime;
    }

    public String getUserIdNo() {
        return userIdNo;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStrJournalImage(String strJournalImage) {
        this.strJournalImage = strJournalImage;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public void setStrJournalContent(String strJournalContent) {
        this.strJournalContent = strJournalContent;
    }

    public void setStrCreateTime(String strCreateTime) {
        this.strCreateTime = strCreateTime;
    }

    public void setStrUpdateTime(String strUpdateTime) {
        this.strUpdateTime = strUpdateTime;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    public void setUserIdNo(String userIdNo) {
        this.userIdNo = userIdNo;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
