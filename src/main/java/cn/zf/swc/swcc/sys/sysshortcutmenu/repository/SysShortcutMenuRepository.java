package cn.zf.swc.swcc.sys.sysshortcutmenu.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysShortcutMenuRepository extends CommonRepository<SysShortcutMenu, String> {
    List<SysShortcutMenu> findByUserId(String userId);
}
