package cn.zf.swc.swcc.setinfo.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SetInfoVo extends PageCondition implements Serializable {


    private Long id;//厕位id

    private Long zigbeeMId;//总线ID

    private Long zigbeeBId;//厕位标识ID

    private Long setId;//客户端厕位ID

    private Integer wcType;//厕所类型

    private String wcTypeName;//厕所类型名称

    private String macCode;//物理地址

    private Long wcId;//厕所ID

    private WcInfoVo wcInfoVo;//厕所信息
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间

}
