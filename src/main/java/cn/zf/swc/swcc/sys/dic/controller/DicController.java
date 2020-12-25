package cn.zf.swc.swcc.sys.dic.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sys.dic.pojo.Dic;
import cn.zf.swc.swcc.sys.dic.service.DicService;
import cn.zf.swc.swcc.sys.dic.vo.DicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sys/dic/")
public class DicController extends CommonController<DicVo, Dic, Long> {
    @Autowired
    private DicService dicService;

    @GetMapping("dic")
    public ModelAndView dicType(){
        return new ModelAndView("sys/dic/dic");
    }
}
