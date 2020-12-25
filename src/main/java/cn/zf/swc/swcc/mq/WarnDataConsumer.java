package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.warndata.pojo.WarnData;
import cn.zf.swc.swcc.warndata.repository.WarnDataRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.WarnDataDto;

import java.util.Date;


@Component
@RabbitListener(queues = "warnDataQueue")
public class WarnDataConsumer {
    @Autowired
    private WarnDataRepository warnDataRepository;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(WarnDataDto warnDataDto) {
        WarnData warnData = new WarnData();
        warnData.setMacCode(warnDataDto.getMacCode());
        warnData.setTime(new Date(Long.valueOf(warnDataDto.getTime())));
        warnData.setSwitchId(Long.valueOf(warnDataDto.getSwitchId()));
        warnData.setStatus(Long.valueOf(warnDataDto.getStatus()));
        warnData.setWcId(Long.valueOf(warnDataDto.getWcId()));
        warnData.setCreateTime(new Date());
        warnData.setUpdateTime(new Date());
        this.warnDataRepository.save(warnData);
    }

}