package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;

import java.util.List;

public interface SetDataService extends CommonService<SetDataVo, SetData, Long> {

    Result<List<WcStatisticsInfo>> geTotalUsageByDay(Long wcId, String macCode,int day);

    Long findSumSetDataNumber(Long wcId,String macCode);

    Long findSumSetDataNumberYesterday(Long wcId, String macCode);

    Long findManSumFlow(Long wcId, String macCode);

    Long findWoManSumFlow(Long wcId, String macCode);

    Long findManFlow(Long wcId, String macCode);

    Long findWoManFlow(Long wcId, String macCode);

    Double findAvgTime(Long wcId, String macCode);

    List<SetDataVo> findBy(Long wcId, String macCode);
}
