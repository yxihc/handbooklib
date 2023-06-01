package com.taopao.oldbase.base.bean;

import java.io.Serializable;

/**
 * Created by blacKey on 2016/5/4.
 */
public class Menstruation implements Serializable {
    private int id;
    private String personname;
    private String idcard;
    private String recorddate;
    private int offsetDay;

    public Menstruation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(String recorddate) {
        this.recorddate = recorddate;
    }

    public int getOffsetDay() {
        return offsetDay;
    }

    public void setOffsetDay(int offsetDay) {
        this.offsetDay = offsetDay;
    }
}
