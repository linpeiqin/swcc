package cn.zf.swc.swcc.sensordata.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cwc_sensor_data")
@Data
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//id

    private Long modbusId;//总线ID

    private Long sensorType;//传感器类型

    private Long sensorId;//传感器ID

    private Date time;//创建时间

    private Double value1;

    private Double value2;

    private Double value3;

    private String macCode;//物理地址

    private Long wcId;

}
