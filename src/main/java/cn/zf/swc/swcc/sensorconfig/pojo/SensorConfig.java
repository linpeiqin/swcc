package cn.zf.swc.swcc.sensorconfig.pojo;

import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
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

    private Date time;//创建时间

    private String macCode;//物理地址

    @ManyToOne
    @JoinColumn(name="WC_ID")
    private WcInfo wcInfo;//厕所信息

}
