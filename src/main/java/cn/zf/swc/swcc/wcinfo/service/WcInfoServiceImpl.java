package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.util.CopyUtil;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WcInfoServiceImpl extends CommonServiceImpl<WcInfoVo, WcInfo, Long> implements WcInfoService {
    @Autowired
    private WcInfoRepository  wcInfoRepository;

    @Override
    public Result<List<WcInfoVo>> listByA() {
        return Result.of(CopyUtil.copyList(this.wcInfoRepository.findAll(),WcInfoVo.class));
    }

    @Override
    public Result<List<WcStatisticsInfo>> listByDay(int day) {
        return null/* Result.of(this.wcInfoRepository.findByDay(day))*/;
    }
}
