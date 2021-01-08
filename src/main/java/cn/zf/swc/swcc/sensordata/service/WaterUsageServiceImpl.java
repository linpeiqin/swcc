package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.WaterUsage;
import cn.zf.swc.swcc.sensordata.repository.WaterUsageRepository;
import cn.zf.swc.swcc.sensordata.specification.WaterUsageSpecification;
import cn.zf.swc.swcc.sensordata.vo.WaterUsageVo;
import cn.zf.swc.swcc.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WaterUsageServiceImpl extends CommonServiceImpl<WaterUsageVo, WaterUsage, Long> implements WaterUsageService {
    @Autowired
    private WaterUsageRepository waterUsageRepository;

    @Autowired
    private WaterUsageSpecification wateryUsageSpecification;
    @Override
    public Result<List<WaterUsageVo>> list(WaterUsageVo entityVo) {
        return Result.of(CopyUtil.copyList(waterUsageRepository.findAll(wateryUsageSpecification.getWaterUsageSpecification(entityVo),new Sort(Sort.Direction.ASC,"id")),  WaterUsageVo.class));
    }

    @Override
    public Double getWaterUsageV(Long wcId, String macCode) {
        return this.waterUsageRepository.getWaterUsageV(wcId,macCode);
    }
}
