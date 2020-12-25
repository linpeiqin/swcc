package cn.zf.swc.swcc.sensorconfig.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cwc_sensor_config")
@Data
public class SensorConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//传感器id

    private Long modbusId;//总线ID

    private Long sensorType;//传感器类型

    private Long outId;//输出ID

    private Double limitVal;//动作值

    private Double limitDownVal;//解除值

    private String macCode;//物理地址

    private Long wcId;//厕所ID

    private Long wcInfoId;//厕所信息ID

    private Long sensorId;//客户端传感器ID

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private Integer status;//连接状态

    private Date statusTime;//状态更新时间

}
