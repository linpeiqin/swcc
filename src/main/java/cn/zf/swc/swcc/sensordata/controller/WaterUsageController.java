package cn.zf.swc.swcc.sensordata.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sensordata.pojo.WaterUsage;
import cn.zf.swc.swcc.sensordata.service.WaterUsageService;
import cn.zf.swc.swcc.sensordata.vo.WaterUsageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/waterUsage/")
public class WaterUsageController extends CommonController<WaterUsageVo, WaterUsage, Long> {
    @Autowired
    private WaterUsageService waterUsageService;

    @GetMapping("waterUsage")
    public ModelAndView waterUsage(){
        return new ModelAndView("sensordata/waterusage");
    }
}
