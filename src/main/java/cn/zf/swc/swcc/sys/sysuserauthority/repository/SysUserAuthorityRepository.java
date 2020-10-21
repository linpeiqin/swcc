package cn.zf.swc.swcc.sys.sysuserauthority.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sys.sysuserauthority.pojo.SysUserAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserAuthorityRepository extends CommonRepository<SysUserAuthority, String> {
    List<SysUserAuthority> findByUserId(String userId);
}
