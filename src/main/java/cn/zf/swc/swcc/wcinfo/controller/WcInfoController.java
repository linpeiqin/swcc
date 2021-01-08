package cn.zf.swc.swcc.wcinfo.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.util.SysSettingUtil;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStateInfo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/wc/wcInfo/")
public class WcInfoController extends CommonController<WcInfoVo, WcInfo, Long> {
    @Autowired
    private WcInfoService wcInfoService;
    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;
    @GetMapping("wcInfo")
    public ModelAndView wcInfo(){
        return new ModelAndView("wcinfo/wcinfo");
    }
    @GetMapping("wcInfoSelect")
    public Result<List<WcInfoVo>> wcInfoSelect(){
        return this.wcInfoService.selectByAuthentication();
    }
    @GetMapping("wcInfoForState")
    public Result<List<WcStateInfo>> wcInfoForState(){
        return this.wcInfoService.wcInfoForStatistices();
    }



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
