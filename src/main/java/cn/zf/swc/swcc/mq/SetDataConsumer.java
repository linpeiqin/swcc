package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.repository.SetDataRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wc.dto.SetDataDto;

import java.sql.Date;


@Component
@RabbitListener(queues = "setDataQueue")
public class SetDataConsumer {
    @Autowired
    private SetDataRepository setDataRepository;

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SetDataDto setDataDto) {
        SetData setData = new SetData();
        setData.setMacCode(setDataDto.getMacCode());
        setData.setStartTime(new Date(Long.valueOf(setDataDto.getStartTime())));
        setData.setTime(Long.valueOf(setDataDto.getTime()));
        setData.setWcId(Long.valueOf(setDataDto.getWcId()));
        setData.setWcSetId(Long.valueOf(setDataDto.getWcSetId()));
        setData.setWcType(Long.valueOf(setDataDto.getWcSetType()));
        this.setDataRepository.save(setData);
    }

}