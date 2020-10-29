package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.WcInfoDto;


@Component
@RabbitListener(queues = "wcInfoQueue")
public class WcInfoConsumer {
    @Autowired
    private WcInfoRepository wcInfoRepository;
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(WcInfoDto wcInfoDto) {
        WcInfo wcInfo  = new WcInfo();
        wcInfo.setInfo(wcInfoDto.getInfo());
        wcInfo.setLocation(String.valueOf(wcInfoDto.getLocation()));
        wcInfo.setPassword(wcInfoDto.getPassword());
        this.wcInfoRepository.save(wcInfo);
    }

}