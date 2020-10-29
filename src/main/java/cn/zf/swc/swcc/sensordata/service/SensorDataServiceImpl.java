package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SensorDataServiceImpl extends CommonServiceImpl<SensorDataVo, SensorData, Long> implements SensorDataService {
}
