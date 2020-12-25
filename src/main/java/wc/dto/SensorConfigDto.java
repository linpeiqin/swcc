package wc.dto;

import java.io.Serializable;

public class SensorConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int modbusId;
    private int sensorType;
    private int outId;
    private double limitVal;
    private double limitDownVal;
    private String messageId;
    private String macCode;
    private int wcId;
    private String opt;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

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

    public int getModbusId() {
        return modbusId;
    }

    public void setModbusId(int modbusId) {
        this.modbusId = modbusId;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    public int getOutId() {
        return outId;
    }

    public void setOutId(int outId) {
        this.outId = outId;
    }

    public double getLimitVal() {
        return limitVal;
    }

    public void setLimitVal(double limitVal) {
        this.limitVal = limitVal;
    }

    public double getLimitDownVal() {
        return limitDownVal;
    }

    public void setLimitDownVal(double limitDownVal) {
        this.limitDownVal = limitDownVal;
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
