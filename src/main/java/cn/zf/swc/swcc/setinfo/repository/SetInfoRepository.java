package cn.zf.swc.swcc.setinfo.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;

public interface SetInfoRepository extends CommonRepository<SetInfo, Long> {
    SetInfo findByWcIdAndMacCodeAndSetId(Long wcId, String macCode, Long setId);
}
