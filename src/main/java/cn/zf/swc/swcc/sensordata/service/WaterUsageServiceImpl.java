package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.WaterUsage;
import cn.zf.swc.swcc.sensordata.vo.WaterUsageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WaterUsageServiceImpl extends CommonServiceImpl<WaterUsageVo, WaterUsage, Long> implements WaterUsageService {
}
