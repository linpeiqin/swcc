package cn.zf.swc.swcc.sys.sysusermenu.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sys.sysmenu.vo.SysMenuVo;
import cn.zf.swc.swcc.sys.sysusermenu.pojo.SysUserMenu;
import cn.zf.swc.swcc.sys.sysusermenu.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, String> {
    Result<List<SysMenuVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String menuIdList);
}
