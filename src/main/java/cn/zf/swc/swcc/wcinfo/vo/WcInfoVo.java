package cn.zf.swc.swcc.wcinfo.vo;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WcInfoVo extends PageCondition implements Serializable {
    private Long id;//厕所id

    private String info;//厕所名称
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date recordTime;//记录时间

    private Long location;//厕所位置

    private Long wcId;//客户端厕所ID

    private String password;//密码

    private String macCode;//物理地址

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;//修改时间

    private Integer status;//连接状态
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date statusTime;//状态更新时间

    private String wcIdAndMacCode;

    public String getWcIdAndMacCode() {
        return this.wcId+"|"+this.macCode;
    }

    private String statusName;

    public String getStatusName() {
        if (status.equals(1)){
            return "在线";
        }
        return "离线";
    }
}
