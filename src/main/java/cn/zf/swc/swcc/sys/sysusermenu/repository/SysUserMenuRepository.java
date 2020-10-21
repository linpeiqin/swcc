package cn.zf.swc.swcc.sys.sysusermenu.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sys.sysusermenu.pojo.SysUserMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMenuRepository extends CommonRepository<SysUserMenu, String> {
    List<SysUserMenu> findByUserId(String userId);
}
