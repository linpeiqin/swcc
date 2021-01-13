package cn.zf.swc.swcc.setinfo.repository;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.vo.SetInfoVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetInfoRepository extends CommonRepository<SetInfo, Long> {
    SetInfo findByWcIdAndMacCodeAndSetId(Long wcId, String macCode, Long setId);
    @Query(value = "select count(*) as number from SetInfo setInfo where setInfo.wcInfoId = ?1")
    Long findSumSetNumber(Long wcId);
    @Query(value = "select count(*) as number from SetInfo setInfo where setInfo.wcInfoId = ?1 and setInfo.wcType = 1")
    Long findSumManSetNumber(Long wcId);
    @Query(value = "select count(*) as number from SetInfo setInfo where setInfo.wcInfoId = ?1 and setInfo.wcType = 2")
    Long findSumWomanSetNumber(Long wcId);

    List<SetInfo> findAllByWcIdAndMacCode(Long wcId, String macCode);
}
