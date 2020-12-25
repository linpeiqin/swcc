package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import cn.zf.swc.swcc.sensordata.repository.EnergyUsageRepository;
import cn.zf.swc.swcc.sensordata.specification.EnergyUsageSpecification;
import cn.zf.swc.swcc.sensordata.vo.EnergyUsageVo;
import cn.zf.swc.swcc.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnergyUsageServiceImpl extends CommonServiceImpl<EnergyUsageVo, EnergyUsage, Long> implements EnergyUsageService {

    @Autowired
    private EnergyUsageRepository energyUsageRepository;

    @Autowired
    private EnergyUsageSpecification energyUsageSpecification;
    @Override
    public Result<List<EnergyUsageVo>> list(EnergyUsageVo entityVo) {
        return Result.of(CopyUtil.copyList(energyUsageRepository.findAll(energyUsageSpecification.getEnergyUsageSpecification(entityVo),new Sort(Sort.Direction.ASC,"id")),  EnergyUsageVo.class));
    }
}
