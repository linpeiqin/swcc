package cn.zf.swc.swcc.sys.sysshortcutmenu.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sys.sysshortcutmenu.pojo.SysShortcutMenu;
import cn.zf.swc.swcc.sys.sysshortcutmenu.service.SysShortcutMenuService;
import cn.zf.swc.swcc.sys.sysshortcutmenu.vo.SysShortcutMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/sysShortcutMenu/")
public class SysShortcutMenuController extends CommonController<SysShortcutMenuVo, SysShortcutMenu, String> {
    @Autowired
    private SysShortcutMenuService sysShortcutMenuService;
}
