package wc.dto;

import java.io.Serializable;

public class WarnDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int wcId;
    private int switchId;
    private String time;
    private String disposeTime;
    private int status;
    private String disposeInfo;
    private String messageId;
    private String macCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWcId() {
        return wcId;
    }

    public void setWcId(int wcId) {
        this.wcId = wcId;
    }

    public int getSwitchId() {
        return switchId;
    }

    public void setSwitchId(int switchId) {
        this.switchId = switchId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(String disposeTime) {
        this.disposeTime = disposeTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDisposeInfo() {
        return disposeInfo;
    }

    public void setDisposeInfo(String disposeInfo) {
        this.disposeInfo = disposeInfo;
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
