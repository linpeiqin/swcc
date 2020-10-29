package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WcInfoServiceImpl extends CommonServiceImpl<WcInfoVo, WcInfo, Long> implements WcInfoService {
}
