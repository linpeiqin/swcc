package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensordata.pojo.WaterUsage;
import cn.zf.swc.swcc.sensordata.repository.WaterUsageRepository;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.WaterDataDto;

import java.util.Date;
import java.util.Optional;


@Component
@RabbitListener(queues = "waterDataQueue")
public class WaterDataConsumer {
    @Autowired
    private WaterUsageRepository waterUsageRepository;
    @Autowired
    private WcInfoRepository wcInfoRepository;
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(WaterDataDto waterDataDto) {
        WcInfo wcInfo = this.wcInfoRepository.findByWcIdAndMacCode(Long.valueOf(waterDataDto.getWcId()), waterDataDto.getMacCode());
        if (wcInfo == null){
            return ;
        }
        WaterUsage waterUsage = new WaterUsage();
         Optional<WaterUsage> waterUsages = this.waterUsageRepository.findById(waterDataDto.getDate());
        if (waterUsages.isPresent()){
            waterUsage = waterUsages.get();
        } else {
            waterUsage.setCreateTime(new Date());
        }
        waterUsage.setUpdateTime(new Date());
        waterUsage.setMacCode(waterDataDto.getMacCode());
        waterUsage.setVal(waterDataDto.getVal());
        waterUsage.setId(waterDataDto.getDate());
        waterUsage.setWcId(Long.valueOf(waterDataDto.getWcId()));
        this.waterUsageRepository.save(waterUsage);
    }

}