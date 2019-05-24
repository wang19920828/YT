package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.AdverPlace;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyy
 * @since 2018-10-26
 */
public interface AdverPlaceService extends IService<AdverPlace> {
	public AdverPlace selectBySign(String placeSign);
}
