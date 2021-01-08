package cn.zf.swc.swcc.sensorconfig.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SensorConfigVo extends PageCondition implements Serializable {

    private Long id;//传感器id

    private Long modbusId;//总线ID

    private Long sensorType;//传感器类型

    private String sensorTypeName;//传感器类型

    private Long outId;//输出ID

    private Double limitVal;//动作值

    private Double limitDownVal;//解除值

    private Date time;

    private String macCode;//物理地址

    private WcInfoVo wcInfoVo;//厕所信息

    private Long wcId;//厕所ID

    private Long wcInfoId;//厕所信息ID

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间

    private Integer status;//连接状态

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date statusTime;//状态更新时间
    private String statusName;

    public String getStatusName() {
        if (status.equals(1)){
            return "在线";
        }
        return "离线";
    }
}
