package cn.zf.swc.swcc.wcinfo.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/wcInfo/")
public class WcInfoController extends CommonController<WcInfoVo, WcInfo, Long> {
    @Autowired
    private WcInfoService wcInfoService;

    @GetMapping("wcInfo")
    public ModelAndView wcInfo(){
        return new ModelAndView("wcinfo/wcinfo");
    }
}
