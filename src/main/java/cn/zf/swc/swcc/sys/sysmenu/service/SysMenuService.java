package cn.zf.swc.swcc.sys.sysmenu.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.*;
import cn.zf.swc.swcc.sys.sysmenu.pojo.SysMenu;
import cn.zf.swc.swcc.sys.sysmenu.vo.SysMenuVo;

import java.util.List;

public interface SysMenuService extends CommonService<SysMenuVo, SysMenu, String> {
    Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo);
}
