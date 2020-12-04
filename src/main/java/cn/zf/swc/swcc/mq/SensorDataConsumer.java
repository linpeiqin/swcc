package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.repository.SensorDataRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SensorDataDto;

import java.sql.Date;


@Component
@RabbitListener(queues = "sensorDataQueue")
public class SensorDataConsumer {
    @Autowired
    private SensorDataRepository sensorDataRepository;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SensorDataDto sensorDataDto) {
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
        this.sensorDataRepository.save(sensorData);
    }

}