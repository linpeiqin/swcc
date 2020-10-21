package cn.zf.swc.swcc.sys.sysuserauthority.controller;

import cn.zf.swc.swcc.annotation.Decrypt;
import cn.zf.swc.swcc.annotation.Encrypt;
import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sys.sysauthority.service.SysAuthorityService;
import cn.zf.swc.swcc.sys.sysauthority.vo.SysAuthorityVo;
import cn.zf.swc.swcc.sys.sysuserauthority.pojo.SysUserAuthority;
import cn.zf.swc.swcc.sys.sysuserauthority.service.SysUserAuthorityService;
import cn.zf.swc.swcc.sys.sysuserauthority.vo.SysUserAuthorityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/sysUserAuthority/")
public class SysUserAuthorityController extends CommonController<SysUserAuthorityVo, SysUserAuthority, String> {
    @Autowired
    private SysUserAuthorityService sysUserAuthorityService;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @PostMapping("findUserAuthorityAndAllSysAuthorityByUserId")
    @Decrypt
    @Encrypt
    public Result<Map<String, Object>> findUserAuthorityAndAllSysAuthorityByUserId(SysUserAuthorityVo sysUserAuthorityVo) {
        HashMap<String, Object> map = new HashMap<>();
        List<SysUserAuthorityVo> sysUserAuthorityVoList = sysUserAuthorityService.findByUserId(sysUserAuthorityVo.getUserId()).getData();
        map.put("sysUserAuthorityVoList", sysUserAuthorityVoList);
        List<SysAuthorityVo> sysAuthorityVoList = sysAuthorityService.list(new SysAuthorityVo()).getData();
        map.put("sysAuthorityVoList", sysAuthorityVoList);
        return Result.of(map);
    }

    @PostMapping("saveAllByUserId")
    @Decrypt
    @Encrypt
    public Result<Boolean> saveAllByUserId(SysUserAuthorityVo sysUserAuthorityVo) {
        return sysUserAuthorityService.saveAllByUserId(sysUserAuthorityVo.getUserId(), sysUserAuthorityVo.getAuthorityIdList());
    }
}
