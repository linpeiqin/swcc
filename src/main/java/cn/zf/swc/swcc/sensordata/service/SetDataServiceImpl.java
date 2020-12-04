package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.repository.SetDataRepository;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public Result<List<WcStatisticsInfo>> listByDay(Long wcId, int day) {
        List<Map<String,String>>  listMap =  this.setDataRepository.listByDay(wcId,day);
        List<WcStatisticsInfo> wcStatisticsInfos = new ArrayList<WcStatisticsInfo>();
        for (Map<String,String> map : listMap){
            wcStatisticsInfos.add(new WcStatisticsInfo(map.get("date").toString(),Integer.valueOf(map.get("number"))));
        }
        return Result.of(wcStatisticsInfos);
    }
}
