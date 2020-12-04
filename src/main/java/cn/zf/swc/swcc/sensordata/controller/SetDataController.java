package cn.zf.swc.swcc.sensordata.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.service.SetDataService;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wc/setData/")
public class SetDataController extends CommonController<SetDataVo, SetData, Long> {

    @Autowired
    private SetDataService setDataService;

    @GetMapping("setData")
    public ModelAndView setData(){
        return new ModelAndView("sensordata/setdata");
    }


    @GetMapping(value = "/getTotalUsage")
    public Result<List<WcStatisticsInfo>> geTotalUsageByDay(Long wcId,int day){
        return this.setDataService.listByDay(wcId,day);
    }
}
