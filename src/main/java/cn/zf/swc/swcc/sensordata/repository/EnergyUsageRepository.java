package cn.zf.swc.swcc.sensordata.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import org.springframework.data.jpa.repository.Query;

public interface EnergyUsageRepository extends CommonRepository<EnergyUsage, Long> {
    @Query(value = "select energyUsage.val  from cwc_energy_usage energyUsage where energyUsage.wc_id =?1 and energyUsage.mac_code =?2 order by energyUsage.id desc limit 1", nativeQuery = true)
    Double getEnergyUsageV(Long wcId, String macCode);
}
