package cn.zf.swc.swcc.sys.sysmenu.controller;

import cn.zf.swc.swcc.annotation.Decrypt;
import cn.zf.swc.swcc.annotation.Encrypt;
import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sys.sysmenu.pojo.SysMenu;
import cn.zf.swc.swcc.sys.sysmenu.service.SysMenuService;
import cn.zf.swc.swcc.sys.sysmenu.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sys/sysMenu/")
public class SysMenuController extends CommonController<SysMenuVo, SysMenu, String> {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("menu")
    public ModelAndView menu(){
        return new ModelAndView("sys/menu/menu");
    }

    /**
     * 分层级
     */
    @PostMapping("listByTier")
    @Decrypt
    @Encrypt
    public Result<List<SysMenuVo>> listByTier(SysMenuVo entityVo) {
        return sysMenuService.listByTier(entityVo);
    }
}
