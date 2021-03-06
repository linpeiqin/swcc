package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfoUser;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoUserVo;

import java.util.List;

public interface WcInfoUserService extends CommonService<WcInfoUserVo, WcInfoUser, String> {
    Result<List<WcInfoUserVo>> findByWcInfoId(Long wcInfoId);
    Result<List<WcInfoUserVo>> findByUserId(String userId);
    Result<Boolean> saveAllByWcInfoId(Long wcInfoId, String userIdList);
}
