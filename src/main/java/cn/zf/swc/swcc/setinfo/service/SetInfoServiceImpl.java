package cn.zf.swc.swcc.setinfo.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.vo.SetInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetInfoServiceImpl extends CommonServiceImpl<SetInfoVo, SetInfo, Long> implements SetInfoService {
}
