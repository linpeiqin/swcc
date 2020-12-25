package wc.dto;

import java.io.Serializable;

public class SetInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int zigbeeModuleId;
    private int zigbeeBitId;
    private int wcType;
    private int wcId;
    private String messageId;
    private String macCode;
    private String opt;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZigbeeModuleId() {
        return zigbeeModuleId;
    }

    public void setZigbeeModuleId(int zigbeeModuleId) {
        this.zigbeeModuleId = zigbeeModuleId;
    }

    public int getZigbeeBitId() {
        return zigbeeBitId;
    }

    public void setZigbeeBitId(int zigbeeBitId) {
        this.zigbeeBitId = zigbeeBitId;
    }

    public int getWcType() {
        return wcType;
    }

    public void setWcType(int wcType) {
        this.wcType = wcType;
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
