package cn.zf.swc.swcc.largescreen.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.util.SysSettingUtil;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/largescreen/")
public class LargeScreenController extends CommonController<WcInfoVo, WcInfo, Long> {
    @Autowired
    private WcInfoService wcInfoService;
    @Value("${server.port}")
    private String port;

    /**
     * 跳转大屏幕展示
     */
    @GetMapping("largeScreen")
    public ModelAndView largeScreen(Long wcInfoId) {
        WcInfoVo wcInfoVo = this.wcInfoService.get(wcInfoId).getData();
        ModelAndView modelAndView = new ModelAndView("largescreen/largeScreen.html","port",port);
        modelAndView.addObject("sys", SysSettingUtil.getSysSetting());
        modelAndView.addObject("wcInfoVo",wcInfoVo);
        return modelAndView;
    }
}
