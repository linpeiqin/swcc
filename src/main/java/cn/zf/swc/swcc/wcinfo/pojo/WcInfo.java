package cn.zf.swc.swcc.wcinfo.pojo;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "cwc_info")
@Data
public class WcInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//厕所id

    private String info;//厕所名称

    private Date recordTime;//记录时间

    private Long location;//厕所位置

    private Long wcId;//客户端厕所ID

    private String password;//密码

    private String macCode;//物理地址

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private Integer status;//连接状态

    private Date statusTime;//状态更新时间

    @JoinColumn(name = "wcInfoId")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SetInfo> setInfos;//厕位信息

    @JoinColumn(name = "wcInfoId")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SensorConfig> sensorConfigs;//厕位信息

    @JoinColumn(name = "wcInfoId")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<WcInfoUser> wcInfoUser;//厕位信息
    @Override
    public int hashCode(){
        return new Random().hashCode();
    }
}
