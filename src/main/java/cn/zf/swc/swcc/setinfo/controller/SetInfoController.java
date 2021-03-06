package cn.zf.swc.swcc.setinfo.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.service.SetInfoService;
import cn.zf.swc.swcc.setinfo.vo.SetInfoVo;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/wc/setInfo/")
public class SetInfoController extends CommonController<SetInfoVo, SetInfo, Long> {
    @Autowired
    private SetInfoService setInfoService;
    @GetMapping("setInfo")
    public ModelAndView setInfo(){
        return new ModelAndView("setinfo/setinfo");
    }

    @GetMapping("setInfoList")
    public Result<List<SetInfoVo>> setInfoList(@RequestParam Long wcId, @RequestParam String macCode){
        return this.setInfoService.setInfoList(wcId,macCode);
    }

}
