package cn.zf.swc.swcc.largescreen.vo;

import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LargeScreenVo {

    private Long todaySumFlow;//今日总流量
    private Long yesterdaySumFlow;//昨日总流量
    private Double dayGrowth;//环比增长
    private Long sumSetNumber;//总厕位数
    private Long manSetNumber;//男厕位数
    private Long womanSetNumber;//女厕位数
    private Double avgTime;//平均时长
    private Long manFlow;//男厕所流量
    private Long womanFlow;//女厕所流量
    private Long manSumFlow;//男厕总流量
    private Long womanSumFlow;//女厕总流量
    private Double energyUsageV;//用电量
    private Double WaterUsageV;//用水量
    private Boolean isSensorDataUpdate = false;
    private Boolean isSetDataUpdate = false;
    private List<Integer> statusList; //状态list
    private List<Integer> manStatusList;//男厕状态list
    private List<Integer> womanStatusList;//女厕状态list
    private List<WcStatisticsInfo> wcStatisticsInfos;//七天使用量统计
    private List<SetDataVo>  setDataVos;//实时流量数据;
    private List<SensorDataVo> sensorDataVos;//实施传感器数据；
}
