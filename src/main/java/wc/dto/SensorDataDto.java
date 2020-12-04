package wc.dto;

import java.io.Serializable;

public class SensorDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int wcId;
    private int modbusId;
    private int sensorType;
    private int sensorId;
    private String time;        // 记录的时间
    private volatile double value1; // 水表中对应水的读数，电表中对应电量，空气质量中对应温度，氨气检测值中对应氨气值，激光粉尘中对应PM2。5
    private volatile double value2; // 空气质量中对应湿度， 激光粉尘中对应pm1.0, 电表中对应电压
    private volatile double value3; // 激光粉尘中对应pm10
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

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getValue3() {
        return value3;
    }

    public void setValue3(double value3) {
        this.value3 = value3;
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
