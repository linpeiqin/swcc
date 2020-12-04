package cn.zf.swc.swcc.sensordata.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cwc_energy_usage")
@Data
public class EnergyUsage {

    @Id
    private Long id;//传感器id

    private Double val;//值

    private String macCode;//物理地址

    private Long wcId;
}
