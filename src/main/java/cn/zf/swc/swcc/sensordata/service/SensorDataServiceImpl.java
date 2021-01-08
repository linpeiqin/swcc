package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.repository.SensorDataRepository;
import cn.zf.swc.swcc.sensordata.specification.SensorDataSpecification;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.sys.dic.service.DicService;
import cn.zf.swc.swcc.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SensorDataServiceImpl extends CommonServiceImpl<SensorDataVo, SensorData, Long> implements SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;//注入实体类仓库

    @Autowired
    private DicService dicService;//注入实体类仓库
    @Autowired
    private SensorDataSpecification sensorDataSpecification;

    @Override
    public Result<PageInfo<SensorDataVo>> page(SensorDataVo entityVo) {
        entityVo.setSidx("time");
        entityVo.setSordBy(Sort.Direction.DESC);
        Page<SensorData> page = sensorDataRepository.findAll(sensorDataSpecification.getSensorDataSpecification(entityVo), entityVo.getPageable());
        PageInfo<SensorDataVo> sensorDataPage = PageInfo.of(page,  SensorDataVo.class);
        List<SensorDataVo> content = sensorDataPage.getRows();
        for (SensorDataVo sensorDataVo : content){
            sensorDataVo.setValueText();
            String value = dicService.findByTagAndKey("SENSOR_TYPE_TAG",String.valueOf(sensorDataVo.getSensorType()));
            if (value != null){
                sensorDataVo.setSensorTypeName(value);
            }
        }
        return Result.of(sensorDataPage);
    }

    @Override
    public List<SensorDataVo> findBy(Long wcId, String macCode) {
        List<SensorDataVo> sensorDataVos = CopyUtil.copyList(this.sensorDataRepository.findby(wcId,macCode),SensorDataVo.class);
        for (SensorDataVo sensorDataVo : sensorDataVos){
            sensorDataVo.setValueText();
            String value = dicService.findByTagAndKey("SENSOR_TYPE_TAG",String.valueOf(sensorDataVo.getSensorType()));
            if (value != null){
                sensorDataVo.setSensorTypeName(value);
            }
        }
        return sensorDataVos;
    }

    @Override
    public Long findSumNumber(Long wcId, String macCode) {
        return this.sensorDataRepository.findSumNumber(wcId,macCode);
    }
}
