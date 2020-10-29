package cn.zf.swc.swcc.sensordata.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SetDataVo extends PageCondition implements Serializable {
    private Long id;//传感器id

    private Long wcId;

    private Long wcSetId;

    private Date startTime;

    private Long wcType;

    private Long time;//持续时间时间

    private String macCode;//物理地址

}
