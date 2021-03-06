package cn.zf.swc.swcc.setinfo.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.vo.SetInfoVo;

import java.util.List;

public interface SetInfoService extends CommonService<SetInfoVo, SetInfo, Long> {

    Long findSumSetNumber(Long wcId);

    Long findSumManSetNumber(Long wcId);

    Long findSumWomanSetNumber(Long wcId);

    Result<List<SetInfoVo>> setInfoList(Long wcId, String macCode);
}
