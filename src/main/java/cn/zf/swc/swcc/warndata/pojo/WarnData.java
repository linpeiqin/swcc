package cn.zf.swc.swcc.warndata.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cwc_warn_data")
@Data
public class WarnData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//id
    private Long wcId;//厕所ID
    private Long switchId;//转换器ID
    private Date time;//报警时间
    private Date disposeTime;//处置时间
    private Long status;//状态
    private String disposeInfo;//处置信息
    private String macCode;//物理地址

}
