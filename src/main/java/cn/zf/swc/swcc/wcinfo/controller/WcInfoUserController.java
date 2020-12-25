package cn.zf.swc.swcc.wcinfo.controller;

import cn.zf.swc.swcc.annotation.Decrypt;
import cn.zf.swc.swcc.annotation.Encrypt;
import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sys.sysuser.service.SysUserService;
import cn.zf.swc.swcc.sys.sysuser.vo.SysUserVo;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfoUser;
import cn.zf.swc.swcc.wcinfo.service.WcInfoUserService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wc/wcInfoUser/")
public class WcInfoUserController extends CommonController<WcInfoUserVo, WcInfoUser, String> {
    @Autowired
    private WcInfoUserService wcInfoUserService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("findWcInfoUserAndAllUserByWcInfoId")
    @Decrypt
    @Encrypt
    public Result<Map<String,Object>> findWcInfoUserAndAllUserByWcInfoId(WcInfoUserVo wcInfoUserVo){
        HashMap<String, Object> map = new HashMap<>();
        List<WcInfoUserVo> wcInfoUserVoList = wcInfoUserService.findByWcInfoId(wcInfoUserVo.getWcInfoId()).getData();
        map.put("wcInfoUserVoList",wcInfoUserVoList);
        List<SysUserVo> sysUserVoList = sysUserService.list(new SysUserVo()).getData();
        map.put("sysUserVoList",sysUserVoList);
        return Result.of(map);
    }

    @PostMapping("saveAllByWcInfoId")
    @Decrypt
    @Encrypt
    public Result<Boolean> saveAllByWcInfoId(WcInfoUserVo wcInfoUserVo) {
        return wcInfoUserService.saveAllByWcInfoId(wcInfoUserVo.getWcInfoId(), wcInfoUserVo.getUserIdList());
    }

}
