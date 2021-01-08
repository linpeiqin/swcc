package cn.zf.swc.swcc.largescreen.util;

import cn.zf.swc.swcc.largescreen.vo.LargeScreenVo;
import cn.zf.swc.swcc.sensordata.service.EnergyUsageService;
import cn.zf.swc.swcc.sensordata.service.SensorDataService;
import cn.zf.swc.swcc.sensordata.service.SetDataService;
import cn.zf.swc.swcc.sensordata.service.WaterUsageService;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.setinfo.service.SetInfoService;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;
import java.util.List;

@Slf4j
@Configuration
public class LargeScreenUtil {
    @Autowired
    private WcInfoService wcInfoService;

    @Autowired
    private SetInfoService setInfoService;

    @Autowired
    private SetDataService setDataService;

    @Autowired
    private WaterUsageService waterUsageService;

    @Autowired
    private EnergyUsageService energyUsageService;

    @Autowired
    private SensorDataService sensorDataService;
    private  ThreadLocal<Long> lastSetDataNumber = new ThreadLocal<>();
    private  ThreadLocal<Long> lastSensorDataNumber = new ThreadLocal<>();
    public LargeScreenVo setSetData(Long wcInfoId,LargeScreenVo largeScreenVoB) {
        if (wcInfoId == null) {
            return null;
        }
        LargeScreenVo largeScreenVo = largeScreenVoB;
        if (largeScreenVo == null){
            largeScreenVo = new LargeScreenVo();
        }
        WcInfoVo wcInfoVo = wcInfoService.get(wcInfoId).getData();
        Long sumSetDataNumber = this.setDataService.findSumSetDataNumber(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        Long sumSetNumber = this.setInfoService.findSumSetNumber(wcInfoVo.getId());
        Long sumManSetNumber = this.setInfoService.findSumManSetNumber(wcInfoVo.getId());
        Long sumWomanSetNumber = this.setInfoService.findSumWomanSetNumber(wcInfoVo.getId());
        Long sumSetDataNumberYesterday = this.setDataService.findSumSetDataNumberYesterday(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        Long manSumFlow = this.setDataService.findManSumFlow(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        Long woManSumFlow = this.setDataService.findWoManSumFlow(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        Long manFlow = this.setDataService.findManFlow(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        Long woManFlow = this.setDataService.findWoManFlow(wcInfoVo.getWcId(),wcInfoVo.getMacCode());


        Double aveTime = this.setDataService.findAvgTime(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        List<WcStatisticsInfo> wcStatisticsInfos = this.setDataService.geTotalUsageByDay(wcInfoVo.getWcId(),wcInfoVo.getMacCode(),6).getData();
        List<SetDataVo> setDataVos = this.setDataService.findBy(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        largeScreenVo.setSumSetNumber(sumSetNumber);
        largeScreenVo.setManSetNumber(sumManSetNumber);
        largeScreenVo.setWomanSetNumber(sumWomanSetNumber);
        largeScreenVo.setTodaySumFlow(sumSetDataNumber);
        largeScreenVo.setYesterdaySumFlow(sumSetDataNumberYesterday);
        largeScreenVo.setManSumFlow(manSumFlow);
        largeScreenVo.setWomanSumFlow(woManSumFlow);
        largeScreenVo.setManFlow(manFlow);
        largeScreenVo.setWomanFlow(woManFlow);
        if (sumSetDataNumberYesterday!=0L){
            Double dayGrowth =Double.valueOf(sumSetDataNumber - sumSetDataNumberYesterday)/Double.valueOf(sumSetDataNumberYesterday)*100;
            largeScreenVo.setDayGrowth(dayGrowth);
        }
        if (setDataVos!=null && setDataVos.size()>0){
            largeScreenVo.setSetDataVos(setDataVos);
        }
        if (wcStatisticsInfos!=null){
            largeScreenVo.setWcStatisticsInfos(wcStatisticsInfos);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        if (aveTime!=null){
            largeScreenVo.setAvgTime(Double.valueOf(df.format(aveTime/1000)));
        }
        largeScreenVo.setIsSetDataUpdate(true);
        return largeScreenVo;
    }

    public LargeScreenVo setSensorData(Long wcInfoId,LargeScreenVo largeScreenVoB){
        if (wcInfoId == null) {
            return null;
        }
        LargeScreenVo largeScreenVo = largeScreenVoB;
        if (largeScreenVo == null){
            largeScreenVo = new LargeScreenVo();
        }
        WcInfoVo wcInfoVo = wcInfoService.get(wcInfoId).getData();
        List<SensorDataVo> sensorDataVo = this.sensorDataService.findBy(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        if (sensorDataVo!=null && sensorDataVo.size()>0){
            largeScreenVo.setSensorDataVos(sensorDataVo);
        }
        Double waterUsageV = this.waterUsageService.getWaterUsageV(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        Double energyUsageV = this.energyUsageService.getEnergyUsageV(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        largeScreenVo.setWaterUsageV(waterUsageV);
        largeScreenVo.setEnergyUsageV(energyUsageV);
        largeScreenVo.setIsSensorDataUpdate(true);
        return largeScreenVo;
    }
    public Boolean isSetDataUpdate(Long wcInfoId){
        WcInfoVo wcInfoVo = wcInfoService.get(wcInfoId).getData();
        Long sumSetDataNumber = this.setDataService.findSumSetDataNumber(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        if (sumSetDataNumber.equals(lastSetDataNumber.get())){
            return false;
        }
        lastSetDataNumber.set(sumSetDataNumber);
       return true;
    }
    public Boolean isSensorDataUpdate(Long wcInfoId){
        WcInfoVo wcInfoVo = wcInfoService.get(wcInfoId).getData();
        Long sensorDataNumber = this.sensorDataService.findSumNumber(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
        if (sensorDataNumber.equals(lastSensorDataNumber.get())){
            return false;
        }
        lastSensorDataNumber.set(sensorDataNumber);
        return true;
    }

    public LargeScreenVo getLargeScreenInfo(Long wcInfoId) {
        LargeScreenVo largeScreenVo = null;
        largeScreenVo = setSetData(wcInfoId,largeScreenVo);
        largeScreenVo = setSensorData(wcInfoId,largeScreenVo);
        return largeScreenVo;
    }
}
