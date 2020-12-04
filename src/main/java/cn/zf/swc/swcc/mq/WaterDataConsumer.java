package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import cn.zf.swc.swcc.sensordata.pojo.WaterUsage;
import cn.zf.swc.swcc.sensordata.repository.WaterUsageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.WaterDataDto;

import java.util.List;
import java.util.Optional;


@Component
@RabbitListener(queues = "waterDataQueue")
public class WaterDataConsumer {
    @Autowired
    private WaterUsageRepository waterUsageRepository;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(WaterDataDto waterDataDto) {
        WaterUsage waterUsage = new WaterUsage();
         Optional<WaterUsage> waterUsages = this.waterUsageRepository.findById(waterDataDto.getDate());
        if (waterUsages.isPresent()){
            waterUsage = waterUsages.get();
        }
        waterUsage.setMacCode(waterDataDto.getMacCode());
        waterUsage.setVal(waterDataDto.getVal());
        waterUsage.setId(waterDataDto.getDate());
        this.waterUsageRepository.save(waterUsage);
    }

}