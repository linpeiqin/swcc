package cn.zf.swc.swcc.sensorconfig.task;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.repository.SensorConfigRepository;
import cn.zf.swc.swcc.sensorconfig.service.SensorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class SensorConfigTask {
    @Autowired
    private SensorConfigRepository sensorConfigRepository;

    @Scheduled(fixedRate = 30000)
    private void configureTask() {
        List<SensorConfig> sensorConfigList = sensorConfigRepository.findAll();
        for (SensorConfig sensorConfig : sensorConfigList) {
            if (sensorConfig.getStatusTime() == null){
                sensorConfig.setStatus(0);
            } else {
                if (new Date().getTime() - sensorConfig.getStatusTime().getTime() > 30000){
                    sensorConfig.setStatus(0);
                }
            }
        }
        sensorConfigRepository.saveAll(sensorConfigList);
    }
}
