package cn.zf.swc.swcc.sensorconfig.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SensorConfigVo extends PageCondition implements Serializable {
    private Long id;//传感器id

    private Long modbusId;//总线ID

    private Long sensorType;//传感器类型

    private Long outId;//输出ID

    private Double limitVal;//动作值

    private Double limitDownVal;//解除值

    private Date time;//创建时间

    private String macCode;//物理地址

}
