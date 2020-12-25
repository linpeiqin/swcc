package cn.zf.swc.swcc.sensorconfig.service;

import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.repository.SensorConfigRepository;
import cn.zf.swc.swcc.sensorconfig.specification.SensorConfigSpecification;
import cn.zf.swc.swcc.sensorconfig.vo.SensorConfigVo;
import cn.zf.swc.swcc.sys.dic.service.DicService;
import cn.zf.swc.swcc.util.CopyUtil;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SensorConfigServiceImpl extends CommonServiceImpl<SensorConfigVo, SensorConfig, Long> implements SensorConfigService {
    @Autowired
    private WcInfoService wcInfoService;
    @Autowired
    private DicService dicService;//注入实体类仓库
    @Autowired
    private SensorConfigRepository sensorConfigRepository;//注入实体类仓库
    @Autowired
    private SensorConfigSpecification sensorConfigSpecification;
    @Override
    public Result<PageInfo<SensorConfigVo>> page(SensorConfigVo entityVo) {
        Page<SensorConfig> page = sensorConfigRepository.findAll(sensorConfigSpecification.getSensorConfigSpecification(entityVo), entityVo.getPageable());
        PageInfo<SensorConfigVo> sensorConfigPage = PageInfo.of(page,  SensorConfigVo.class);
        List<SensorConfigVo> content = sensorConfigPage.getRows();
        for (SensorConfigVo sensorConfigVo : content){
            sensorConfigVo.setWcInfoVo(wcInfoService.findVoByWcIdAndMacCode(sensorConfigVo.getWcId(),sensorConfigVo.getMacCode()));
            String value = dicService.findByTagAndKey("SENSOR_TYPE_TAG",String.valueOf(sensorConfigVo.getSensorType()));
            if (value != null){
                sensorConfigVo.setSensorTypeName(value);
            }
        }
        return Result.of(sensorConfigPage);
    }
}
