package wc.dto;

import java.io.Serializable;

public class EnergyDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int wcId;
    private long date;
    private double val;
    private String messageId;
    private String macCode;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public int getWcId() {
        return wcId;
    }

    public void setWcId(int wcId) {
        this.wcId = wcId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }
}
