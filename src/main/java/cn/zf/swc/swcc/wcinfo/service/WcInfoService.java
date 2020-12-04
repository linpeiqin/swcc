package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;

import java.util.List;

public interface WcInfoService extends CommonService<WcInfoVo, WcInfo, Long> {

    Result<List<WcInfoVo>> listByA();

    Result<List<WcStatisticsInfo>> listByDay(int day);
}
