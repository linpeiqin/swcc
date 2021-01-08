package cn.zf.swc.swcc.wcinfo.service;

import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.service.SetDataService;
import cn.zf.swc.swcc.setinfo.service.SetInfoService;
import cn.zf.swc.swcc.sys.sysuser.service.SysUserService;
import cn.zf.swc.swcc.sys.sysuser.vo.SysUserVo;
import cn.zf.swc.swcc.util.CopyUtil;
import cn.zf.swc.swcc.util.SecurityUtil;
import cn.zf.swc.swcc.wcinfo.pojo.WcInfo;
import cn.zf.swc.swcc.wcinfo.repository.WcInfoRepository;
import cn.zf.swc.swcc.wcinfo.specification.WcInfoSpecification;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoUserVo;
import cn.zf.swc.swcc.wcinfo.vo.WcInfoVo;
import cn.zf.swc.swcc.wcinfo.vo.WcStateInfo;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WcInfoServiceImpl extends CommonServiceImpl<WcInfoVo, WcInfo, Long> implements WcInfoService {
    @Autowired
    private WcInfoRepository wcInfoRepository;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private WcInfoUserService wcInfoUserService;
    @Autowired
    private WcInfoSpecification wcInfoSpecification;
    @Autowired
    private SetInfoService setInfoService;
    @Autowired
    private SetDataService setDataService;
    @Override
    public Result<List<WcInfoVo>> selectByAuthentication() {
        SysUserVo sysUserVo = sysUserService.findByLoginName(SecurityUtil.getLoginUser().getUsername()).getData();
        List<WcInfoUserVo> wcInfoUserVos = wcInfoUserService.findByUserId(sysUserVo.getUserId()).getData();
        List<WcInfoVo> wcInfoVos = new ArrayList<WcInfoVo>();
        for (WcInfoUserVo wcInfoUserVo : wcInfoUserVos) {
            if (wcInfoUserVo.getWcInfoVo() != null) {
                wcInfoVos.add(wcInfoUserVo.getWcInfoVo());
            }
        }
        return Result.of(wcInfoVos);
    }

    @Override
    public Result<PageInfo<WcInfoVo>> page(WcInfoVo entityVo) {
        return Result.of(PageInfo.of(wcInfoRepository.findAll(wcInfoSpecification.getWcInfoSpecification(entityVo), entityVo.getPageable()), WcInfoVo.class));
    }

    @Override
    public WcInfo findByWcIdAndMacCode(Long wcId, String macCode) {
        return wcInfoRepository.findByWcIdAndMacCode(wcId, macCode);
    }

    public WcInfoVo findVoByWcIdAndMacCode(Long wcId, String macCode) {
        return CopyUtil.copy(wcInfoRepository.findByWcIdAndMacCode(wcId, macCode), WcInfoVo.class);
    }

    @Override
    public Result<List<WcStatisticsInfo>> listByDay(int day) {
        return null/* Result.of(this.wcInfoRepository.findByDay(day))*/;
    }

    @Override
    public Result<List<WcStateInfo>> wcInfoForStatistices() {
        List<WcInfoVo> voList = this.selectByAuthentication().getData();
        List<WcStateInfo> wcStateInfos = new ArrayList<>();
        voList.forEach(wcInfoVo -> {
            WcStateInfo wcStateInfo = new WcStateInfo();
            wcStateInfo.setWcInfoVo(wcInfoVo);
            Long sumSetDataNumber = this.setDataService.findSumSetDataNumber(wcInfoVo.getWcId(),wcInfoVo.getMacCode());
            Long sumSetNumber = this.setInfoService.findSumSetNumber(wcInfoVo.getId());
            Long sumManSetNumber = this.setInfoService.findSumManSetNumber(wcInfoVo.getId());
            Long sumWomanSetNumber = this.setInfoService.findSumWomanSetNumber(wcInfoVo.getId());
            wcStateInfo.setSumManSetNumber(sumManSetNumber);
            wcStateInfo.setSumSetDataNumber(sumSetDataNumber);
            wcStateInfo.setSumWomanSetNumber(sumWomanSetNumber);
            wcStateInfo.setSumSetNumber(sumSetNumber);
            wcStateInfos.add(wcStateInfo);
        });
        return Result.of(wcStateInfos);
    }



}
