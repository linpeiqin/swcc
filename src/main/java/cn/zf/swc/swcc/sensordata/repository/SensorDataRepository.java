package cn.zf.swc.swcc.sensordata.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorDataRepository extends CommonRepository<SensorData, Long> {
    @Query(value = "select *  from cwc_sensor_data sensorData where sensorData.wc_id =?1 and sensorData.mac_code =?2 order by sensorData.update_time desc limit 3", nativeQuery = true)
    List<SensorData> findby(Long wcId, String macCode);
    @Query(value = "select count(*) as number  from SensorData sensorData where sensorData.wcId =?1 and sensorData.macCode =?2 and DateDiff(sensorData.time,NOW())=0")
    Long findSumNumber(Long wcId, String macCode);
}
