package cn.zf.swc.swcc.common.service;


import cn.zf.swc.swcc.common.pojo.PageInfo;
import cn.zf.swc.swcc.common.pojo.Result;

import java.util.List;

/**
 * 通用Service
 *
 * @param <V> 实体类Vo
 * @param <E> 实体类
 * @param <T> id主键类型
 */
public interface CommonService<V, E,T> {

    Result<PageInfo<V>> page(V entityVo);

    Result<List<V>> list(V entityVo);

    Result<List<V>> list(V entityVo,String sidx);

    Result<V> get(T id);

    Result<V> save(V entityVo);

    Result<T> delete(T id);
}
