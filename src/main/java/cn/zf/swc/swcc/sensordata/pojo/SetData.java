package cn.zf.swc.swcc.sensordata.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cwc_set_data")
@Data
public class SetData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//传感器id

    private Long wcId;

    private Long wcSetId;

    private Date startTime;

    private Long wcType;

    private Long time;//持续时间时间

    private String macCode;//物理地址

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

}
