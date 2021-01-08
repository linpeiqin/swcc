package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.repository.SetInfoRepository;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SetInfoDto;

import java.util.Date;


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
        WcInfo wcInfo = this.wcInfoRepository.findByWcIdAndMacCode(Long.valueOf(setInfoDto.getWcId()), setInfoDto.getMacCode());
        if (wcInfo == null) {
            return;
        }
        SetInfo setInfo = this.setInfoRepository.findByWcIdAndMacCodeAndSetId(Long.valueOf(setInfoDto.getWcId()), setInfoDto.getMacCode(), Long.valueOf(setInfoDto.getId()));
        if (setInfoDto.getOpt().equals("del")) {
            if (setInfo != null) {
                this.setInfoRepository.delete(setInfo);
            }
            return;
        }
        if (setInfo == null) {
            setInfo = new SetInfo();
            setInfo.setCreateTime(new Date());
        }
        setInfo.setWcInfoId(wcInfo.getId());
        setInfo.setUpdateTime(new Date());
        setInfo.setMacCode(setInfoDto.getMacCode());
        setInfo.setWcType(setInfoDto.getWcType());
        setInfo.setZigbeeBId(Long.valueOf(setInfoDto.getZigbeeBitId()));
        setInfo.setZigbeeMId(Long.valueOf(setInfoDto.getZigbeeModuleId()));
        setInfo.setSetId(Long.valueOf(setInfoDto.getId()));
        setInfo.setWcId(Long.valueOf(setInfoDto.getWcId()));
        this.setInfoRepository.save(setInfo);
    }

}