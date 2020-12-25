package cn.zf.swc.swcc.sensordata.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;//创建时间

    private Double value1;

    private Double value2;

    private Double value3;

    private String macCode;//物理地址

    private Long wcId;

    private Date createTime;//创建时间

    private Date updateTime;//修改时间
}
