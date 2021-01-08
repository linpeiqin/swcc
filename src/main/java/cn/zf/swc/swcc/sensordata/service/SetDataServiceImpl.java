package cn.zf.swc.swcc.sensordata.service;

import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;
import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sensordata.pojo.SetData;
import cn.zf.swc.swcc.sensordata.repository.SetDataRepository;
import cn.zf.swc.swcc.sensordata.specification.SetDataSpecification;
import cn.zf.swc.swcc.sensordata.vo.SetDataVo;
import cn.zf.swc.swcc.sys.dic.service.DicService;
import cn.zf.swc.swcc.util.CopyUtil;
import cn.zf.swc.swcc.wcinfo.vo.WcStatisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SetDataServiceImpl extends CommonServiceImpl<SetDataVo, SetData, Long> implements SetDataService {

    @Autowired
    private SetDataRepository setDataRepository;

    @Autowired
    private DicService dicService;
    @Autowired
    private SetDataSpecification setDataSpecification;

    @Override
    public Result<List<WcStatisticsInfo>> geTotalUsageByDay(Long wcId, String macCode,int day) {
        List<Map<String,String>>  listMap =  this.setDataRepository.geTotalUsageByDay(wcId,macCode,day);
        List<WcStatisticsInfo> wcStatisticsInfos = new ArrayList<WcStatisticsInfo>();
        for (Map<String,String> map : listMap){
            wcStatisticsInfos.add(new WcStatisticsInfo(map.get("DATE").toString(),Integer.valueOf(map.get("NUMBER"))));
        }
        return Result.of(wcStatisticsInfos);
    }

    @Override
    public Long findSumSetDataNumber(Long wcId,String macCode) {
        return this.setDataRepository.findSumSetDataNumber(wcId,macCode);
    }

    @Override
    public Long findSumSetDataNumberYesterday(Long wcId, String macCode) {
        return this.setDataRepository.findSumSetDataNumberYesterday(wcId,macCode);
    }

    @Override
    public Long findManSumFlow(Long wcId, String macCode) {
        return this.setDataRepository.findSumFlowBy(wcId,macCode,1L);
    }

    @Override
    public Long findWoManSumFlow(Long wcId, String macCode) {
        return this.setDataRepository.findSumFlowBy(wcId,macCode,2L);
    }

    @Override
    public Long findManFlow(Long wcId, String macCode) {
        return this.setDataRepository.findFlowBy(wcId,macCode,1L);
    }

    @Override
    public Long findWoManFlow(Long wcId, String macCode) {
        return this.setDataRepository.findFlowBy(wcId,macCode,2L);
    }

    @Override
    public Double findAvgTime(Long wcId, String macCode) {
        return this.setDataRepository.findAvgTime(wcId,macCode);
    }

    @Override
    public List<SetDataVo> findBy(Long wcId, String macCode) {
        List<SetDataVo> setDataVos = CopyUtil.copyList(this.setDataRepository.findBy(wcId,macCode),SetDataVo.class);
        for (SetDataVo setDataVo : setDataVos){
            String value = dicService.findByTagAndKey("WC_TYPE_TAG",String.valueOf(setDataVo.getWcType()));
            if (value != null){
                setDataVo.setWcTypeName(value);
            }
        }
        return setDataVos;
    }

    @Override
    public Result<PageInfo<SetDataVo>> page(SetDataVo entityVo) {
        entityVo.setSidx("startTime");
        entityVo.setSordBy(Sort.Direction.DESC);
        Page<SetData> page = setDataRepository.findAll(setDataSpecification.getSetDataSpecification(entityVo), entityVo.getPageable());
        PageInfo<SetDataVo> setDataPage = PageInfo.of(page,  SetDataVo.class);
        List<SetDataVo> content = setDataPage.getRows();
        for (SetDataVo setDataVo : content){
            String value = dicService.findByTagAndKey("WC_TYPE_TAG",String.valueOf(setDataVo.getWcType()));
            if (value != null){
                setDataVo.setWcTypeName(value);
            }
        }
        return Result.of(setDataPage);
    }

}
