package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetDataServiceImpl extends CommonServiceImpl<SetDataVo, SetData, Long> implements SetDataService {
}
