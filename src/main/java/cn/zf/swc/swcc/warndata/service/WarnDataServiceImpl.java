package cn.zf.swc.swcc.warndata.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.warndata.pojo.WarnData;
import cn.zf.swc.swcc.warndata.vo.WarnDataVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WarnDataServiceImpl extends CommonServiceImpl<WarnDataVo, WarnData, Long> implements WarnDataService {
}
