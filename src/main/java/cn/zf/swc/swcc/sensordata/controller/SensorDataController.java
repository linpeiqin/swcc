package cn.zf.swc.swcc.sensordata.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.service.SensorDataService;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/sensorData/")
public class SensorDataController extends CommonController<SensorDataVo, SensorData, Long> {
    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping("sensorData")
    public ModelAndView sensorData(){
        return new ModelAndView("sensordata/sensordata");
    }

    @Override
    public Result<PageInfo<SensorDataVo>> page(SensorDataVo entityVo) {
        return super.page(entityVo);
    }
}
