package cn.zf.swc.swcc.sensordata.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SensorDataVo extends PageCondition implements Serializable {
    private Long id;//id

    private Long modbusId;//总线ID

    private Long sensorType;//传感器类型

    private Long sensorId;//传感器ID

    private Date time;//创建时间

    private Double value1;

    private Double value2;

    private Double value3;

    private String macCode;//物理地址

}
