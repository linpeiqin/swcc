package cn.zf.swc.swcc.wcinfo.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WcInfoRepository extends CommonRepository<WcInfo, Long> {
    WcInfo findByWcIdAndMacCode(Integer wcId,String macCode);

   /* @Query("select new WcStatisticsInfo(date_format(csd.START_TIME, '%Y-%m-%d') as date, count(1) as number, ci.INFO as wcName) from cwc_info ci,cwc_set_data csd where ci.WC_ID = csd.WC_ID   and DATE_SUB(CURDATE(), INTERVAL 2 DAY) < date_format(START_TIME, '%Y-%m-%d') group by date_format(START_TIME, '%Y-%m-%d'), ci.INFO;")*/
   /* List<WcStatisticsInfo> findByDay(int day);*/
}
