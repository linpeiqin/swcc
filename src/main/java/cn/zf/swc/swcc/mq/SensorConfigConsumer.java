package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.repository.SensorConfigRepository;
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
        SensorConfig sensorConfig = new SensorConfig();
        sensorConfig.setMacCode(sensorConfigDto.getMacCode());
       // sensorConfig.setTime(new Date(sensorConfigDto.get));
        sensorConfig.setLimitDownVal(sensorConfigDto.getLimitDownVal());
        sensorConfig.setLimitVal(sensorConfigDto.getLimitVal());
        sensorConfig.setModbusId(Long.valueOf(sensorConfigDto.getModbusId()));
        sensorConfig.setSensorType(Long.valueOf(sensorConfigDto.getSensorType()));
        sensorConfig.setWcInfo(this.wcInfoRepository.findByWcIdAndMacCode(sensorConfigDto.getWcId(),sensorConfigDto.getMacCode()));
        this.sensorConfigRepository.save(sensorConfig);
    }

}