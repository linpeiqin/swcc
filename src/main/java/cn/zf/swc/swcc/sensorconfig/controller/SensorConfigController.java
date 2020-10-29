package cn.zf.swc.swcc.sensorconfig.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.service.SensorConfigService;
import cn.zf.swc.swcc.sensorconfig.vo.SensorConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/sensorConfig/")
public class SensorConfigController extends CommonController<SensorConfigVo, SensorConfig, Long> {
    @Autowired
    private SensorConfigService sensorConfigService;

    @GetMapping("sensorConfig")
    public ModelAndView sensorConfig(){
        return new ModelAndView("sensorconfig/sensorconfig");
    }
}
