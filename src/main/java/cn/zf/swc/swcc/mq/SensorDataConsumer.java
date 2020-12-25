package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.repository.SensorConfigRepository;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.repository.SensorDataRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SensorDataDto;

import java.util.Date;


@Component
@RabbitListener(queues = "sensorDataQueue")
public class SensorDataConsumer {
    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorConfigRepository sensorConfigRepository;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SensorDataDto sensorDataDto) {
        SensorConfig sensorConfig = this.sensorConfigRepository.findByWcIdAndMacCodeAndSensorId(Long.valueOf(sensorDataDto.getWcId()),sensorDataDto.getMacCode(),Long.valueOf(sensorDataDto.getSensorId()));
        if (sensorConfig==null){
            return ;
        }
        sensorConfig.setStatus(1);
        sensorConfig.setStatusTime(new Date());
        this.sensorConfigRepository.save(sensorConfig);
        SensorData sensorData = new SensorData();
        sensorData.setMacCode(sensorDataDto.getMacCode());
        sensorData.setTime(new Date(Long.valueOf(sensorDataDto.getTime())));
        sensorData.setModbusId(Long.valueOf(sensorDataDto.getModbusId()));
        sensorData.setSensorId(Long.valueOf(sensorDataDto.getSensorId()));
        sensorData.setSensorType(Long.valueOf(sensorDataDto.getSensorType()));
        sensorData.setWcId(Long.valueOf(sensorDataDto.getWcId()));
        sensorData.setValue1(sensorDataDto.getValue1());
        sensorData.setValue2(sensorDataDto.getValue2());
        sensorData.setValue3(sensorDataDto.getValue3());
        sensorData.setCreateTime(new Date());
        sensorData.setUpdateTime(new Date());
        this.sensorDataRepository.save(sensorData);
    }

}