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
            WcInfo wcInfo = this.wcInfoRepository.findByWcIdAndMacCode(Long.valueOf(wcInfoDto.getId()), wcInfoDto.getMacCode());
            if (wcInfo == null) {
                wcInfo = new WcInfo();
                wcInfo.setCreateTime(new Date());
            } else {
                if (wcInfoDto.getOpt().equals("del")){
                    this.wcInfoRepository.delete(wcInfo);
                    return ;
                }
            }
            wcInfo.setUpdateTime(new Date());
            wcInfo.setInfo(wcInfoDto.getInfo());
            wcInfo.setLocation(wcInfoDto.getLocation());
            wcInfo.setWcId(Long.valueOf(wcInfoDto.getId()));
            wcInfo.setRecordTime(wcInfoDto.getRecordTime());
            wcInfo.setPassword(wcInfoDto.getPassword());
            wcInfo.setMacCode(wcInfoDto.getMacCode());
            this.wcInfoRepository.save(wcInfo);
    }

}