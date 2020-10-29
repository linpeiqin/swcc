package cn.zf.swc.swcc.wcinfo.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WcInfoVo extends PageCondition implements Serializable {
    private Long id;//厕所id

    private String info;//厕所名称

    private Date recordTime;//记录时间
    private String location;//厕所位置

    private String password;//密码

    private String macCode;//物理地址

}
