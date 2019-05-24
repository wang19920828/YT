package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.CmsParamDao;
import org.fh.general.ecom.basics.model.CmsParam;
import org.fh.general.ecom.basics.service.CmsParamService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@Service
public class CmsParamServiceImpl extends ServiceImpl<CmsParamDao, CmsParam> implements CmsParamService {
    @Override
    public Map<String, String> selectByParamName(String paramName) {
        Map<String,String> map=new HashMap<String, String>();
        List<CmsParam> list=baseMapper.selectByParamName(paramName);
        for(CmsParam p:list){
            map.put(p.getParamKey(), p.getParamValue());
        }
        return map;
    }
}
