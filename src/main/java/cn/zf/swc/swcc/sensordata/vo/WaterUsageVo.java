package cn.zf.swc.swcc.sensordata.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WaterUsageVo extends PageCondition implements Serializable {
    private Long id;//传感器id

    private Double val;//值

    private String macCode;//物理地址

}
