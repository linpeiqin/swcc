package cn.zf.swc.swcc.wcinfo.repository;

import cn.zf.swc.swcc.common.repository.*;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfoUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WcInfoUserRepository extends CommonRepository<WcInfoUser, String> {
    List<WcInfoUser> findByWcInfoId(Long wcInfoId);
}
