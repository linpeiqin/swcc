package cn.zf.swc.swcc.sensordata.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EnergyUsageVo implements Serializable {
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Long id;

    private Double val;//值

    private String macCode;//物理地址

    private Long wcId;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Long startDate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Long endDate;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间
}
