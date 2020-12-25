package cn.zf.swc.swcc.warndata.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WarnDataVo extends PageCondition implements Serializable {
    private Long id;//id
    private Long wcId;//厕所ID
    private Long switchId;//转换器ID
    private Date time;//报警时间
    private Date disposeTime;//处置时间
    private Long status;//状态
    private String disposeInfo;//处置信息
    private String macCode;//物理地址

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间
}
