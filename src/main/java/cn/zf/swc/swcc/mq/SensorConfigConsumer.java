package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.repository.SensorConfigRepository;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SensorConfigDto;

import java.util.Date;


@Component
@RabbitListener(queues = "sensorConfigQueue")
public class SensorConfigConsumer {
    @Autowired
    private SensorConfigRepository sensorConfigRepository;
    @Autowired
    private WcInfoRepository wcInfoRepository;
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SensorConfigDto sensorConfigDto) {
        WcInfo wcInfo = this.wcInfoRepository.findByWcIdAndMacCode(Long.valueOf(sensorConfigDto.getWcId()),sensorConfigDto.getMacCode());
        if (wcInfo == null){
            return ;
        }
        SensorConfig sensorConfig = this.sensorConfigRepository.findByWcIdAndMacCodeAndSensorId(Long.valueOf(sensorConfigDto.getWcId()),sensorConfigDto.getMacCode(),Long.valueOf(sensorConfigDto.getId()));
        if (sensorConfigDto.getOpt().equals("del")){
            if (sensorConfig != null) {
                this.sensorConfigRepository.delete(sensorConfig);
            }
            return ;
        }
        if (sensorConfig == null){
            sensorConfig = new SensorConfig();
            sensorConfig.setCreateTime(new Date());
        }
        sensorConfig.setWcInfoId(wcInfo.getId());
        sensorConfig.setUpdateTime(new Date());
        sensorConfig.setMacCode(sensorConfigDto.getMacCode());
        sensorConfig.setLimitDownVal(sensorConfigDto.getLimitDownVal());
        sensorConfig.setLimitVal(sensorConfigDto.getLimitVal());
        sensorConfig.setModbusId(Long.valueOf(sensorConfigDto.getModbusId()));
        sensorConfig.setSensorType(Long.valueOf(sensorConfigDto.getSensorType()));
        sensorConfig.setWcId(Long.valueOf(sensorConfigDto.getWcId()));
        sensorConfig.setSensorId(Long.valueOf(sensorConfigDto.getId()));
        this.sensorConfigRepository.save(sensorConfig);
    }

}