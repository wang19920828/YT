package org.fh.general.ecom.basics.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.DictionaryDao;
import org.fh.general.ecom.basics.model.Dictionary;
import org.fh.general.ecom.basics.service.DictionaryService;
import org.fh.general.ecom.common.dto.basics.dictionary.DictionaryListInDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.DictionaryListOutDTO;
import org.fh.general.ecom.common.po.basics.dictionary.DicListOutPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author pjj
 * @since 2018-08-14
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {



    @Override
    public DictionaryListOutDTO findDicListByType(DictionaryListInDTO paramDto){
        EntityWrapper<Dictionary> wrapper = new EntityWrapper<Dictionary>();
        wrapper.eq("type",paramDto.getType());
        wrapper.eq("branch",paramDto.getBranch());
        List<Dictionary> list=baseMapper.selectList(wrapper);
        //改成从缓存里查 TODO

        List<DicListOutPO> listPo= new ArrayList<DicListOutPO>();
        list.forEach((Dictionary  temp)->{
            DicListOutPO poEn=new DicListOutPO();
            BeanUtils.copyProperties(temp,poEn);
            listPo.add(poEn);
        });
        DictionaryListOutDTO  toEn=new DictionaryListOutDTO();
        toEn.setList(listPo);
        return toEn;
    }

}

