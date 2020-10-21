package cn.zf.swc.swcc.sys.sysuserauthority.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sys.sysuserauthority.pojo.SysUserAuthority;
import cn.zf.swc.swcc.sys.sysuserauthority.vo.SysUserAuthorityVo;

import java.util.List;

public interface SysUserAuthorityService extends CommonService<SysUserAuthorityVo, SysUserAuthority, String> {
    Result<List<SysUserAuthorityVo>> findByUserId(String userId);

    Result<Boolean> saveAllByUserId(String userId, String authorityIdList);
}
