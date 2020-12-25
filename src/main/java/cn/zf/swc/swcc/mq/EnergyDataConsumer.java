package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import cn.zf.swc.swcc.sensordata.repository.EnergyUsageRepository;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.EnergyDataDto;

import java.util.Date;
import java.util.Optional;


@Component
@RabbitListener(queues = "energyDataQueue")
public class EnergyDataConsumer {
    @Autowired
    private EnergyUsageRepository energyUsageRepository;
    @Autowired
    private WcInfoRepository wcInfoRepository;
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(EnergyDataDto energyDataDto) {
        WcInfo wcInfo = this.wcInfoRepository.findByWcIdAndMacCode(Long.valueOf(energyDataDto.getWcId()), energyDataDto.getMacCode());
        if (wcInfo == null){
            return ;
        }
        EnergyUsage energyUsage = new EnergyUsage();
        Optional<EnergyUsage> energyUsages = this.energyUsageRepository.findById(energyDataDto.getDate());
        if (energyUsages.isPresent()){
            energyUsage = energyUsages.get();
        } else {
            energyUsage.setCreateTime(new Date());
        }
        energyUsage.setUpdateTime(new Date());
        energyUsage.setMacCode(energyDataDto.getMacCode());
        energyUsage.setVal(energyDataDto.getVal());
        energyUsage.setId(energyDataDto.getDate());
        energyUsage.setWcId(Long.valueOf(energyDataDto.getWcId()));
        this.energyUsageRepository.save(energyUsage);
    }

}