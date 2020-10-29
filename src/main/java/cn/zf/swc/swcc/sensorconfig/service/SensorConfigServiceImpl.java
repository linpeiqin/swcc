package cn.zf.swc.swcc.sensorconfig.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.vo.SensorConfigVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SensorConfigServiceImpl extends CommonServiceImpl<SensorConfigVo, SensorConfig, Long> implements SensorConfigService {
}
