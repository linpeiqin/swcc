package cn.zf.swc.swcc.wcinfo.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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


    private Integer wcId;//客户端厕所ID

    private String password;//密码

    private String macCode;//物理地址

}
