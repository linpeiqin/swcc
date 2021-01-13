package cn.zf.swc.swcc.mq;

import cn.zf.swc.swcc.setinfo.util.Singleton;
import cn.zf.swc.swcc.setinfo.vo.SetStatus;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import wc.dto.SetStatusDto;

import java.util.ArrayList;
import java.util.List;


@Component
@RabbitListener(queues = "setStatusQueue")
public class SetStatusConsumer {

    /**
     * 消息消费
     *
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(SetStatusDto setStatusDto) {
        List<SetStatus> setStatuses = Singleton.getInstance().getSetStatusList();
        Boolean isExist = false;
        if (setStatuses != null) {
            for (SetStatus setStatus : setStatuses) {
                if (setStatus.getMacCode().equals(setStatusDto.getMacCode()) && setStatus.getWcId().equals(Long.valueOf(setStatusDto.getWcId()))) {
                    setStatus.setStatusList(setStatusDto.getStatusList());
                    setStatus.setUpdateTime(setStatusDto.getUpdateTime());
                    isExist = true;
                    break;
                }
            }
        }
        if (!isExist) {
            if (setStatuses == null) {
                setStatuses = new ArrayList<>();
            }
            SetStatus setStatus = new SetStatus();
            setStatus.setMacCode(setStatusDto.getMacCode());
            setStatus.setWcId(Long.valueOf(setStatusDto.getWcId()));
            setStatus.setUpdateTime(setStatusDto.getUpdateTime());
            setStatus.setStatusList(setStatusDto.getStatusList());
            setStatuses.add(setStatus);
        }
        Singleton.getInstance().setSetStatusList(setStatuses);
    }
}