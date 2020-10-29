package cn.zf.swc.swcc.sensordata.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.service.SetDataService;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/setData/")
public class SetDataController extends CommonController<SetDataVo, SetData, Long> {

    @Autowired
    private SetDataService setDataService;

    @GetMapping("setData")
    public ModelAndView setData(){
        return new ModelAndView("sensordata/setData");
    }
}
