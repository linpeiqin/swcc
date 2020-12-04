package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;

import java.util.List;

public interface SetDataService extends CommonService<SetDataVo, SetData, Long> {

    Result<List<WcStatisticsInfo>> listByDay(Long wcId, int day);
}
