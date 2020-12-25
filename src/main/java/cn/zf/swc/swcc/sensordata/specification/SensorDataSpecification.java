package cn.zf.swc.swcc.sensordata.specification;

import cn.zf.swc.swcc.sensordata.pojo.SensorData;
import cn.zf.swc.swcc.sensordata.vo.SensorDataVo;
import cn.zf.swc.swcc.util.SecurityUtil;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.*;


@Component
public class SensorDataSpecification {
    @Autowired
    private WcInfoService wcInfoService;
    public Specification<SensorData> getSensorDataSpecification(SensorDataVo sensorDataVo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Long wcId = sensorDataVo.getWcId();
            if (wcId!=null && !wcId.equals("")){
                list.add(criteriaBuilder.equal(root.get("wcId"), wcId));
            }
            String macCode = sensorDataVo.getMacCode();
            if (macCode!=null && !macCode.equals("")) {
                list.add(criteriaBuilder.equal(root.get("macCode"), macCode));
            }
            Date startTime = sensorDataVo.getStartTime();
            if (startTime != null && !startTime.equals("")) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time").as(Date.class), startTime));
            }
            Date endTime = sensorDataVo.getEndTime();
            if (endTime != null && !endTime.equals("")) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("time").as(Date.class), endTime));
            }

            if (SecurityUtil.hasAuthentication("ROLE_SA")){
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
            if (!SecurityUtil.hasAuthentication("ROLE_SUB_ADMIN") && !SecurityUtil.hasAuthentication("ROLE_SENSORDATA_SELECT")) {
                return criteriaBuilder.equal(criteriaBuilder.literal(1), 0);
            }
            List<WcInfoVo> wcInfoVos = wcInfoService.selectByAuthentication().getData();
            if (wcInfoVos == null || wcInfoVos.size() == 0) {
                return criteriaBuilder.equal(criteriaBuilder.literal(1), 0);
            }
            CriteriaBuilder.In<Long> inWcId = criteriaBuilder.in(root.get("wcId"));
            CriteriaBuilder.In<String> inMacCode = criteriaBuilder.in(root.get("macCode"));
            for (WcInfoVo wcInfoVo1 : wcInfoVos) {
                inWcId.value(wcInfoVo1.getWcId());
                inMacCode.value(wcInfoVo1.getMacCode());
            }
            list.add(inWcId);
            list.add(inMacCode);
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
