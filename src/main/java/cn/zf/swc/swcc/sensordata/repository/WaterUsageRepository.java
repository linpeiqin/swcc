package cn.zf.swc.swcc.sensordata.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sensordata.pojo.WaterUsage;
import org.springframework.data.jpa.repository.Query;

public interface WaterUsageRepository extends CommonRepository<WaterUsage, Long> {
    @Query(value = "select waterUsage.val  from cwc_water_usage waterUsage where waterUsage.wc_id =?1 and waterUsage.mac_code =?2 order by waterUsage.id desc limit 1", nativeQuery = true)
    Double getWaterUsageV(Long wcId, String macCode);
}
