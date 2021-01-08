package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import cn.zf.swc.swcc.sensordata.vo.EnergyUsageVo;

public interface EnergyUsageService extends CommonService<EnergyUsageVo, EnergyUsage, Long> {

    Double getEnergyUsageV(Long wcId, String macCode);
}
