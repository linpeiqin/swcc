package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.repository.SetInfoRepository;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SetInfoDto;


@Component
@RabbitListener(queues = "setInfoQueue")
public class SetInfoConsumer {
    @Autowired
    private SetInfoRepository setInfoRepository;

    @Autowired
    private WcInfoRepository wcInfoRepository;
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SetInfoDto setInfoDto) {
        SetInfo setInfo = new SetInfo();
        setInfo.setMacCode(setInfoDto.getMacCode());
        setInfo.setWcType(setInfoDto.getWcType());
        setInfo.setZigbeeBId(Long.valueOf(setInfoDto.getZigbeeBitId()));
        setInfo.setZigbeeMId(Long.valueOf(setInfoDto.getZigbeeModuleId()));
        setInfo.setSetId(Long.valueOf(setInfoDto.getId()));
        setInfo.setWcInfo(this.wcInfoRepository.findByWcIdAndMacCode(setInfoDto.getWcId(),setInfoDto.getMacCode()));
        this.setInfoRepository.save(setInfo);
    }

}