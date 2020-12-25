package cn.zf.swc.swcc.sys.dic.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DicTypeVo extends PageCondition implements Serializable {
    private Long id;//表ID

    private String name;//字典名称

    private String tag;//标识符

    private Integer sort;//显示顺序

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

}
