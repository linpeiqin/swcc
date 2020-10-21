package cn.zf.swc.swcc.user.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sys.sysuser.vo.SysUserVo;

public interface UserService {
    Result<SysUserVo> updatePassword(String oldPassword, String newPassword);

    Result<SysUserVo> updateUser(SysUserVo sysUserVo);
}
