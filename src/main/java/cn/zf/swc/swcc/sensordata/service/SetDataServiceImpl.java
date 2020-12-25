package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.repository.SetDataRepository;
import cn.zf.swc.swcc.sensordata.specification.SetDataSpecification;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.sys.dic.service.DicService;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SetDataServiceImpl extends CommonServiceImpl<SetDataVo, SetData, Long> implements SetDataService {

    @Autowired
    private SetDataRepository setDataRepository;

    @Autowired
    private DicService dicService;
    @Autowired
    private SetDataSpecification setDataSpecification;

    @Override
    public Result<List<WcStatisticsInfo>> listByDay(Long wcId, int day) {
        List<Map<String,String>>  listMap =  this.setDataRepository.listByDay(wcId,day);
        List<WcStatisticsInfo> wcStatisticsInfos = new ArrayList<WcStatisticsInfo>();
        for (Map<String,String> map : listMap){
            wcStatisticsInfos.add(new WcStatisticsInfo(map.get("date").toString(),Integer.valueOf(map.get("number"))));
        }
        return Result.of(wcStatisticsInfos);
    }

    @Override
    public Result<PageInfo<SetDataVo>> page(SetDataVo entityVo) {
        entityVo.setSidx("startTime");
        entityVo.setSordBy(Sort.Direction.DESC);
        Page<SetData> page = setDataRepository.findAll(setDataSpecification.getSetDataSpecification(entityVo), entityVo.getPageable());
        PageInfo<SetDataVo> setDataPage = PageInfo.of(page,  SetDataVo.class);
        List<SetDataVo> content = setDataPage.getRows();
        for (SetDataVo setDataVo : content){
            String value = dicService.findByTagAndKey("WC_TYPE_TAG",String.valueOf(setDataVo.getWcType()));
            if (value != null){
                setDataVo.setWcTypeName(value);
            }
        }
        return Result.of(setDataPage);
    }
}
