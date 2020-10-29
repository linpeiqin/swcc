package cn.zf.swc.swcc.sensordata.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sensordata.pojo.EnergyUsage;
import cn.zf.swc.swcc.sensordata.service.EnergyUsageService;
import cn.zf.swc.swcc.sensordata.vo.EnergyUsageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/energyUsage/")
public class EnergyUsageController extends CommonController<EnergyUsageVo, EnergyUsage, Long> {
    @Autowired
    private EnergyUsageService energyUsageService;

    @GetMapping("energyUsage")
    public ModelAndView energyUsage(){
        return new ModelAndView("sensordata/energyusage");
    }
}
