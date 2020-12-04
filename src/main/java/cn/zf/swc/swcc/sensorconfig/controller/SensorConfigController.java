package cn.zf.swc.swcc.sensorconfig.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.service.SensorConfigService;
import cn.zf.swc.swcc.sensorconfig.vo.SensorConfigVo;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
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

    @Override
    public Result<PageInfo<SensorConfigVo>> page(SensorConfigVo entityVo) {
        if (entityVo.getWcInfoWcId()!=null){
            WcInfoVo wcInfoVo = new WcInfoVo();
            wcInfoVo.setWcId(entityVo.getWcInfoWcId());
            entityVo.setWcInfoVo(wcInfoVo);
        }
        return super.page(entityVo);
    }
}
