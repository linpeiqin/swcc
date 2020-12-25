package cn.zf.swc.swcc.mq;


import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.WcInfoDto;

import java.util.Date;

@Component
@RabbitListener(queues = "heartBeatConnectionQueue")
public class HeartBeatConnectionConsumer {
    @Autowired
    private WcInfoRepository wcInfoRepository;

    @RabbitHandler
    public void recieved(WcInfoDto wcInfoDto) {
        WcInfo wcInfo = this.wcInfoRepository.findByWcIdAndMacCode(Long.valueOf(wcInfoDto.getId()), wcInfoDto.getMacCode());
        if (wcInfo != null){
            wcInfo.setStatus(1);
            wcInfo.setStatusTime(new Date());
            this.wcInfoRepository.save(wcInfo);
        }
    }
}
