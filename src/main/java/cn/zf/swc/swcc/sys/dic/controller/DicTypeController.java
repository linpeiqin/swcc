package cn.zf.swc.swcc.sys.dic.controller;

import cn.zf.swc.swcc.common.controller.CommonController;
import cn.zf.swc.swcc.sys.dic.pojo.DicType;
import cn.zf.swc.swcc.sys.dic.service.DicTypeService;
import cn.zf.swc.swcc.sys.dic.vo.DicTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sys/dicType/")
public class DicTypeController extends CommonController<DicTypeVo, DicType, Long> {
    @Autowired
    private DicTypeService dicTypeService;

    @GetMapping("dicType")
    public ModelAndView dicType(){
        return new ModelAndView("sys/dic/dictype");
    }
}
