package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;

import java.util.List;

public interface SensorDataService extends CommonService<SensorDataVo, SensorData, Long> {

    List<SensorDataVo> findBy(Long wcId, String macCode);

    Long findSumNumber(Long wcId, String macCode);
}
