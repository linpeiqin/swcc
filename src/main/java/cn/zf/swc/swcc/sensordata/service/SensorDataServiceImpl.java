package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.PageCondition;
import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.repository.SensorDataRepository;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import cn.zf.swc.swcc.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SensorDataServiceImpl extends CommonServiceImpl<SensorDataVo, SensorData, Long> implements SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;//注入实体类仓库

    @Override
    public Result<PageInfo<SensorDataVo>> page(SensorDataVo entityVo) {
        //实体类缺失分页信息
        if (!(entityVo instanceof PageCondition)) {
            throw new RuntimeException("实体类" + SensorDataVo.class.getName() + "未继承PageCondition。");
        }
        PageCondition pageCondition = entityVo;
        Page<SensorData> page = sensorDataRepository.findAll(Example.of(CopyUtil.copy(entityVo, SensorData.class)), pageCondition.getPageable());
        return Result.of(PageInfo.of(page,  SensorDataVo.class));
    }
}
