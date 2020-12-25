package cn.zf.swc.swcc.sensorconfig.specification;

import cn.zf.swc.swcc.sensorconfig.pojo.SensorConfig;
import cn.zf.swc.swcc.sensorconfig.vo.SensorConfigVo;
import cn.zf.swc.swcc.util.SecurityUtil;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class SensorConfigSpecification {
    @Autowired
    private WcInfoService wcInfoService;
    public Specification<SensorConfig> getSensorConfigSpecification(SensorConfigVo sensorConfigVo) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Long wcId = sensorConfigVo.getWcId();
            if (wcId!=null && !wcId.equals("")){
                list.add(criteriaBuilder.equal(root.get("wcId"), wcId));
            }
            String macCode = sensorConfigVo.getMacCode();
            if (macCode!=null && !macCode.equals("")) {
                list.add(criteriaBuilder.equal(root.get("macCode"), macCode));
            }
            if (SecurityUtil.hasAuthentication("ROLE_SA")){
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
            if (!SecurityUtil.hasAuthentication("ROLE_SUB_ADMIN") && !SecurityUtil.hasAuthentication("ROLE_SENSORCONFIG_MGR") && !SecurityUtil.hasAuthentication("ROLE_SENSORCONFIG_SELECT")) {
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
