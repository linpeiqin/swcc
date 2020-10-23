package cn.zf.swc.swcc.sys.sysusermenu.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sys.sysusermenu.pojo.SysUserMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMenuRepository extends CommonRepository<SysUserMenu, String> {
    List<SysUserMenu> findByUserId(String userId);

    @Query("select mu from SysUserMenu mu,SysMenu m where mu.userId = ?1 and mu.menuId = m.menuId order by m.orderNumber asc")
    List<SysUserMenu> findByUserIdOrderBy(String userId);

}
