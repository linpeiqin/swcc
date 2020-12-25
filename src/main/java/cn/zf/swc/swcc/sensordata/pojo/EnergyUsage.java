package cn.zf.swc.swcc.sensordata.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cwc_energy_usage")
@Data
public class EnergyUsage {

    @Id
    private Long id;//传感器id

    private Double val;//值

    private String macCode;//物理地址

    private Long wcId;
    private Date createTime;//创建时间

    private Date updateTime;//修改时间
}
