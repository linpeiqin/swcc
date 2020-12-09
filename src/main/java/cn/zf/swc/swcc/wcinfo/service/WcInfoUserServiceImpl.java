package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.*;
import cn.zf.swc.swcc.sys.sysuserauthority.vo.SysUserAuthorityVo;
import cn.zf.swc.swcc.util.CopyUtil;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfoUser;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoUserVo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class WcInfoUserServiceImpl extends CommonServiceImpl<WcInfoUserVo, WcInfoUser, String> implements WcInfoUserService{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WcInfoUserRepository wcInfoUserRepository;

    @Override
    public Result<List<WcInfoUserVo>> findByWcInfoId(Long wcInfoId) {
        return Result.of(CopyUtil.copyList(wcInfoUserRepository.findByWcInfoId(wcInfoId), WcInfoUserVo.class));
    }

    @Override
    public Result<Boolean> saveAllByWcInfoId(Long wcInfoId, String userIdList) {
        //先删除旧的
        WcInfoUserVo wcInfoUserVo = new WcInfoUserVo();
        wcInfoUserVo.setWcInfoId(wcInfoId);
        list(wcInfoUserVo).getData().forEach((infoUserVo)->{
            delete(infoUserVo.getWcInfoUserId());
        });

        //再保存新的
        for (String userId : userIdList.split(",")) {
            wcInfoUserVo.setUserId(userId);
            save(wcInfoUserVo);
        }
        return Result.of(true);
    }
}
