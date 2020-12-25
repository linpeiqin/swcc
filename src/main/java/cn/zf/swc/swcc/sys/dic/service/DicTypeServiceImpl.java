package cn.zf.swc.swcc.sys.dic.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sys.dic.pojo.DicType;
import cn.zf.swc.swcc.sys.dic.repository.DicTypeRepository;
import cn.zf.swc.swcc.sys.dic.vo.DicTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class DicTypeServiceImpl extends CommonServiceImpl<DicTypeVo, DicType, Long> implements DicTypeService{

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private DicTypeRepository dicTypeRepository;
}
