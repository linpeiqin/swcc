package cn.zf.swc.swcc.setinfo.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import org.springframework.data.jpa.repository.Query;

public interface SetInfoRepository extends CommonRepository<SetInfo, Long> {
    SetInfo findByWcIdAndMacCodeAndSetId(Long wcId, String macCode, Long setId);
    @Query(value = "select count(*) as number from SetInfo setInfo where setInfo.wcInfoId = ?1")
    Long findSumSetNumber(Long wcId);
    @Query(value = "select count(*) as number from SetInfo setInfo where setInfo.wcInfoId = ?1 and setInfo.wcType = 1")
    Long findSumManSetNumber(Long wcId);
    @Query(value = "select count(*) as number from SetInfo setInfo where setInfo.wcInfoId = ?1 and setInfo.wcType = 2")
    Long findSumWomanSetNumber(Long wcId);
}
