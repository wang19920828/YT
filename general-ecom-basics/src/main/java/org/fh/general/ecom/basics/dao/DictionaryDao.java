package org.fh.general.ecom.basics.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.fh.general.ecom.basics.model.Dictionary;

import java.util.Map;

/**
 * <p>
 * 数据字典表 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-08-14
 */
public interface DictionaryDao extends BaseMapper<Dictionary> {

    void  delBatchDictionarys(Map<String,Object> map);

}