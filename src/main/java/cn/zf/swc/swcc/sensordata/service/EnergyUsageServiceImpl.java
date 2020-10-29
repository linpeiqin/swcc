package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import cn.zf.swc.swcc.sensordata.vo.EnergyUsageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnergyUsageServiceImpl extends CommonServiceImpl<EnergyUsageVo, EnergyUsage, Long> implements EnergyUsageService {
}
