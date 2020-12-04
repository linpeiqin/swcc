package wc.dto;

import java.io.Serializable;

public class SetDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int wcId;
    private int wcSetId;
    private String startTime;
    private int time;
    private int wcSetType;
    private String messageId;
    private String macCode;

    public int getWcId() {
        return wcId;
    }

    public void setWcId(int wcId) {
        this.wcId = wcId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWcSetId() {
        return wcSetId;
    }

    public void setWcSetId(int wcSetId) {
        this.wcSetId = wcSetId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getWcSetType() {
        return wcSetType;
    }

    public void setWcSetType(int wcSetType) {
        this.wcSetType = wcSetType;
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
