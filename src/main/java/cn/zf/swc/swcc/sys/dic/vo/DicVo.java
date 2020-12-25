package cn.zf.swc.swcc.sys.dic.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DicVo extends PageCondition implements Serializable {

    private Long id;//表ID

    private String title;//字典名称

    private String tag;//内部标签

    private String value;//字典值

    private Integer typeId;//字典类型ID

    private Integer status;//状态：1在用 2停用

    private String note;//备注

    private Integer sort;//显示顺序

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

}
