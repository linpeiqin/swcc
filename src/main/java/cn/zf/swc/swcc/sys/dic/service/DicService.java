package cn.zf.swc.swcc.sys.dic.service;

import cn.zf.swc.swcc.common.service.CommonService;
import cn.zf.swc.swcc.sys.dic.pojo.Dic;
import cn.zf.swc.swcc.sys.dic.vo.DicVo;

public interface DicService extends CommonService<DicVo, Dic, Long> {
    String findByTagAndKey(String tag, String key);
}
