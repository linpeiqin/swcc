package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.*;
import cn.zf.swc.swcc.sys.sysuserauthority.vo.SysUserAuthorityVo;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfoUser;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoUserVo;

import javax.sound.midi.SoundbankResource;
import java.util.List;

public interface WcInfoUserService extends CommonService<WcInfoUserVo, WcInfoUser, String> {
    Result<List<WcInfoUserVo>> findByWcInfoId(Long wcInfoId);

    Result<Boolean> saveAllByWcInfoId(Long wcInfoId, String userIdList);
}
