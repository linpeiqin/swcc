package cn.zf.swc.swcc.sensordata.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SensorDataVo extends PageCondition implements Serializable {
    private Long id;//id

    private Long modbusId;//总线ID

    private Long sensorType;//传感器类型
    private String sensorTypeName;//传感器类型

    private Long sensorId;//传感器ID
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;//创建时间

    private Double value1;

    private Double value2;

    private Double value3;

    private String macCode;//物理地址
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private String value1Text;
    private String value2Text;
    private String value3Text;
    private Long wcId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间
    public void setValueText(){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        switch (sensorType.intValue()){
            case 0://水表
                value1Text = "用水量:"+ df.format(this.getValue1());
                break;
            case 1:
                value1Text = "用电量:" + df.format(this.getValue1());
                value2Text = "电压:" + df.format(this.getValue2());
                break;
            case 2:
                value1Text = "氨气浓度:" + df.format(this.getValue1());
                break;
            case 3:
                value1Text = "PM2.5:" + df.format(this.getValue1());
                value2Text = "PM1.0:" + df.format(this.getValue2());
                value3Text = "PM10:" + df.format(this.getValue3());
                break;
            case 4:
                value1Text = "温度:" + df.format(this.getValue1());
                value2Text = "湿度:" + df.format(this.getValue2());
                break;
            case 5:
                value1Text = "湿度: " + df.format(this.getValue1()) + "%";
                value2Text = "温度: " + df.format(this.getValue2()) + "摄氏度";
                break;
            case 6:
                value1Text = "PM2.5: " + df.format(this.getValue1()) + "ug/m3";
                break;
            case 7:
                value1Text = "氨气: " + df.format(this.getValue1()) + "ppm";
                value2Text = "硫化氢: " + df.format(this.getValue2()) + "ppm";
                break;
            default:
                break;
        }
    }
}
