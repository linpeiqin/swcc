package cn.zf.swc.swcc.wcinfo.task;

import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class WcInfoTask {
    @Autowired
    private WcInfoRepository wcInfoRepository;

    @Scheduled(fixedRate = 30000)
    private void heartBeatConnectionTask() {
        List<WcInfo> wcInfoList = wcInfoRepository.findAll();
        for (WcInfo wcInfo : wcInfoList) {
            if (wcInfo.getStatusTime() == null){
                wcInfo.setStatus(0);
            } else {
                if (new Date().getTime() - wcInfo.getStatusTime().getTime() > 30000){
                    wcInfo.setStatus(0);
                }
            }
        }
        wcInfoRepository.saveAll(wcInfoList);
    }
}
