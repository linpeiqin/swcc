package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.repository.SetDataRepository;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.repository.SetInfoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SetDataDto;

import java.util.Date;


@Component
@RabbitListener(queues = "setDataQueue")
public class SetDataConsumer {
    @Autowired
    private SetDataRepository setDataRepository;

    @Autowired
    private SetInfoRepository setInfoRepository;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SetDataDto setDataDto) {
        SetInfo setInfo = this.setInfoRepository.findByWcIdAndMacCodeAndSetId(Long.valueOf(setDataDto.getWcId()),setDataDto.getMacCode(),Long.valueOf(setDataDto.getWcSetId()));
        if (setInfo==null){
            return ;
        }
        SetData setData = new SetData();
        setData.setMacCode(setDataDto.getMacCode());
        setData.setStartTime(new Date(Long.valueOf(setDataDto.getStartTime())));
        setData.setTime(Long.valueOf(setDataDto.getTime()));
        setData.setWcId(Long.valueOf(setDataDto.getWcId()));
        setData.setWcSetId(Long.valueOf(setDataDto.getWcSetId()));
        setData.setWcType(Long.valueOf(setDataDto.getWcSetType()));
        setData.setCreateTime(new Date());
        setData.setUpdateTime(new Date());
        this.setDataRepository.save(setData);
    }

}