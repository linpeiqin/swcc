package cn.zf.swc.swcc.sensordata.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@Repository
public interface SetDataRepository extends CommonRepository<SetData, Long> {
    @Query(value = "SELECT\n" +
            "A.CLICK_DATE AS DATE,IFNULL(B.NUMBER, 0) AS NUMBER\n" +
            "FROM(SELECT CURDATE() AS CLICK_DATE UNION ALL\n" +
            "SELECT\n" +
            "DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS CLICK_DATE\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS CLICK_DATE\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS CLICK_DATE\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "DATE_SUB(CURDATE(), INTERVAL 4 DAY) AS CLICK_DATE\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "DATE_SUB(CURDATE(), INTERVAL 5 DAY) AS CLICK_DATE\n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS CLICK_DATE\n" +
            ") A\n" +
            "LEFT JOIN (\n" +
            "SELECT\n" +
            "date(start_Time) AS DATE,\n" +
            "COUNT(1) AS NUMBER,\n" +
            "WC_ID,\n" +
            "MAC_CODE\n" +
            "FROM\n" +
            "cwc_set_data\n" +
            "WHERE\n" +
            "wc_id = ?1\n" +
            "AND MAC_CODE =?2\n" +
            "AND DATE_SUB(curdate(), INTERVAL 6 DAY) <= date(start_Time)\n" +
            "GROUP BY\n" +
            "date(start_Time)\n" +
            ") B ON A.CLICK_DATE = B.DATE\n" +
            "ORDER BY\n" +
            "DATE ASC", nativeQuery = true)
    List<Map<String, String>> geTotalUsageByDay(Long wcId, String macCode, int day);

    @Query(value = "select count(*) as number  from SetData setData where setData.wcId =?1 and setData.macCode =?2 and DateDiff(setData.startTime,NOW())=0")
    Long findSumSetDataNumber(Long wcId, String macCode);
    @Query(value = "select count(*) as number  from SetData setData where setData.wcId =?1 and setData.macCode =?2 and DateDiff(setData.startTime,NOW())=-1")
    Long findSumSetDataNumberYesterday(Long wcId, String macCode);
    @Query(value = "select count(*) as number  from SetData setData where setData.wcId =?1 and setData.macCode =?2 and setData.wcType =?3")
    Long findSumFlowBy(Long wcId, String macCode, Long wcType);
    @Query(value = "select count(*) as number  from SetData setData where setData.wcId =?1 and setData.macCode =?2 and setData.wcType =?3 and DateDiff(setData.startTime,NOW())=0")
    Long findFlowBy(Long wcId, String macCode, Long l);
    @Query(value = "select avg(setData.time) as avgTime  from SetData setData where setData.wcId =?1 and setData.macCode =?2 and DateDiff(setData.startTime,NOW())=0")
    Double findAvgTime(Long wcId, String macCode);
    @Query(value = "select *  from cwc_set_data setData where setData.wc_id =?1 and setData.mac_code =?2 order by setData.start_time desc limit 5", nativeQuery = true)
    List<SetData> findBy(Long wcId, String macCode);
}
