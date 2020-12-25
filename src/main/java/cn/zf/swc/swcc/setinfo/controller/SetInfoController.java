package cn.zf.swc.swcc.setinfo.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.vo.SetInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/wc/setInfo/")
public class SetInfoController extends CommonController<SetInfoVo, SetInfo, Long> {

    @GetMapping("setInfo")
    public ModelAndView setInfo(){
        return new ModelAndView("setinfo/setinfo");
    }

}
