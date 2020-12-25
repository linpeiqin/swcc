package cn.zf.swc.swcc.sys.dic.repository;

import cn.zf.swc.swcc.common.repository.CommonRepository;
import cn.zf.swc.swcc.sys.dic.pojo.Dic;
import org.springframework.stereotype.Repository;

@Repository
public interface DicRepository extends CommonRepository<Dic, Long> {
    Dic findByTag(String tag);
}
