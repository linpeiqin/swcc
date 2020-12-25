package cn.zf.swc.swcc.wcinfo.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import cn.zf.swc.swcc.sys.sysuser.vo.SysUserVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WcInfoUserVo extends PageCondition implements Serializable {
    private String wcInfoUserId;//厕所用户表id

    private String userId;//用户id

    private Long wcInfoId;//厕所id

    private SysUserVo sysUserVo;//用户

    private WcInfoVo wcInfoVo;//厕所信息

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间

    private String userIdList;////新增、修改厕所关联用户时用户id集合

}
