package wc.dto;

import java.io.Serializable;
import java.util.List;

public class SetStatusDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int wcId;
    private String macCode;
    private String updateTime;
    private List<Integer> statusList;
    private String messageId;

    public int getWcId() {
        return wcId;
    }

    public void setWcId(int wcId) {
        this.wcId = wcId;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
