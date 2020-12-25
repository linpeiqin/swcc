package cn.zf.swc.swcc.sys.dic.service;

import cn.zf.swc.swcc.common.service.CommonServiceImpl;
import cn.zf.swc.swcc.sys.dic.pojo.Dic;
import cn.zf.swc.swcc.sys.dic.repository.DicRepository;
import cn.zf.swc.swcc.sys.dic.vo.DicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DicServiceImpl extends CommonServiceImpl<DicVo, Dic, Long> implements DicService{

    @Autowired
    private DicRepository dicRepository;

    @Override
    public String findByTagAndKey(String tag, String key) {
        Dic dic = dicRepository.findByTag(tag);
        if (dic==null){
            return null;
        }
        List<String> values = Arrays.asList(dic.getValue().split("\n"));
        for (String value : values){
            String[] keyValue = value.split(":");
            if (key.equals(keyValue[0])){
                return keyValue[1];
            }
        }
        return null;
    }
}
