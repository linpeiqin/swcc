package cn.zf.swc.swcc.sys.sysuser.repository;

import cn.zf.swc.swcc.common.repository.*;
import cn.zf.swc.swcc.sys.sysuser.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, String> {
    SysUser findByLoginName(String username);
}
