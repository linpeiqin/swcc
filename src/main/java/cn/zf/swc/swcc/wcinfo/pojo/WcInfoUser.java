package cn.zf.swc.swcc.wcinfo.pojo;

import cn.zf.swc.swcc.sys.sysuser.pojo.SysUser;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "CWC_WC_INFO_USER")
@Data
public class WcInfoUser implements Serializable {
    @Id
    private String wcInfoUserId;//厕所用户表id

    private String userId;//用户id

    private Long wcInfoId;//厕所id

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    @OneToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private SysUser sysUser;//用户

    @OneToOne
    @JoinColumn(name = "wcInfoId",referencedColumnName = "id", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private WcInfo wcInfo;//厕所信息

    @Override
    public int hashCode(){
        return new Random().hashCode();
    }

}
