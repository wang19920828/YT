package org.fh.general.ecom.basics.service.impl;

import org.fh.general.ecom.basics.model.AdverPlace;
import org.fh.general.ecom.basics.dao.AdverPlaceDao;
import org.fh.general.ecom.basics.service.AdverPlaceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-10-26
 */
@Service
public class AdverPlaceServiceImpl extends ServiceImpl<AdverPlaceDao, AdverPlace> implements AdverPlaceService {
    @Autowired
    private AdverPlaceDao adverPlaceDao;
    @Override
    public AdverPlace selectBySign(String placeSign) {
        AdverPlace entity=adverPlaceDao.selectBySign(placeSign);
        return entity ;
    }
}
