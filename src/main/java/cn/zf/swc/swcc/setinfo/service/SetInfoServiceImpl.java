package cn.zf.swc.swcc.setinfo.service;

import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.setinfo.pojo.SetInfo;
import cn.zf.swc.swcc.setinfo.repository.SetInfoRepository;
import cn.zf.swc.swcc.setinfo.specification.SetInfoSpecification;
import cn.zf.swc.swcc.setinfo.vo.SetInfoVo;
import cn.zf.swc.swcc.sys.dic.service.DicService;
import cn.zf.swc.swcc.util.CopyUtil;
import cn.zf.swc.swcc.wcinfo.service.WcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SetInfoServiceImpl extends CommonServiceImpl<SetInfoVo, SetInfo, Long> implements SetInfoService {

    @Autowired
    private WcInfoService wcInfoService;
    @Autowired
    private SetInfoRepository setInfoRepository;//注入实体类仓库

    @Autowired
    private DicService dicService;//注入实体类仓库
    @Autowired
    private SetInfoSpecification setInfoSpecification;

    @Override
    public Result<PageInfo<SetInfoVo>> page(SetInfoVo entityVo) {
        Page<SetInfo> page = setInfoRepository.findAll(setInfoSpecification.getSetInfoSpecification(entityVo), entityVo.getPageable());
        PageInfo<SetInfoVo> setInfoPage = PageInfo.of(page,  SetInfoVo.class);
        List<SetInfoVo> content = setInfoPage.getRows();
        for (SetInfoVo setInfoVo : content){
            setInfoVo.setWcInfoVo(wcInfoService.findVoByWcIdAndMacCode(setInfoVo.getWcId(),setInfoVo.getMacCode()));
            String value = dicService.findByTagAndKey("WC_TYPE_TAG",String.valueOf(setInfoVo.getWcType()));
            if (value != null){
                setInfoVo.setWcTypeName(value);
            }
        }
        return Result.of(setInfoPage);
    }

}
