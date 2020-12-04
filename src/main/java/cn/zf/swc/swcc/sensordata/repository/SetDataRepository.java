package cn.zf.swc.swcc.sensordata.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SetDataRepository extends CommonRepository<SetData, Long> {
    @Query("select date_format(setData.startTime,'%Y-%m-%d')  as date,count(1) as number from SetData setData where  setData.wcId = ?1 group by date_format(setData.startTime, '%Y-%m-%d')")
    List<Map<String,String>> listByDay(Long wcId, int day);
}
