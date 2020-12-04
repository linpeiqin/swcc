package cn.zf.swc.swcc.setinfo.pojo;

import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cwc_set_info")
@Data
public class SetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//厕位id

    @Column(name = "ZIGBEE_M_ID")
    private Long zigbeeMId;//总线ID
    @Column(name = "ZIGBEE_B_ID")
    private Long zigbeeBId;//厕位标识ID

    private Long setId;//客户端厕位ID

    private Date createTime;//创建时间

    private Integer wcType;//厕所类型

    private String macCode;//物理地址

    @ManyToOne
    @JoinColumn(name="WC_ID")
    private WcInfo wcInfo;//厕所信息

}
