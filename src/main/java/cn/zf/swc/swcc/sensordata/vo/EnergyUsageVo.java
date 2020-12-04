package cn.zf.swc.swcc.sensordata.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class EnergyUsageVo extends PageCondition implements Serializable {
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Long id;//传感器id

    private Double val;//值

    private String macCode;//物理地址
    private Long wcId;
}
