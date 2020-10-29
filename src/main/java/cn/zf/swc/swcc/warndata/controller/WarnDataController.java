package cn.zf.swc.swcc.warndata.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.warndata.pojo.WarnData;
import cn.zf.swc.swcc.warndata.service.WarnDataService;
import cn.zf.swc.swcc.warndata.vo.WarnDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/warnData/")
public class WarnDataController extends CommonController<WarnDataVo, WarnData, Long> {
    @Autowired
    private WarnDataService warnDataService;

    @GetMapping("warnData")
    public ModelAndView warnData(){
        return new ModelAndView("warndata/warndata");
    }
}
