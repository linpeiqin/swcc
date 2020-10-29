package cn.zf.swc.swcc.setinfo.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SetInfoVo extends PageCondition implements Serializable {
    private Long id;//厕位id

    private Long zigbeeMId;//总线ID

    private Long zigbeeBId;//厕位标识ID

    private Date createTime;//创建时间

    private Integer wcType;//厕所类型

    private String macCode;//物理地址

}
