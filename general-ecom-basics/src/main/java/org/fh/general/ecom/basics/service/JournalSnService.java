package org.fh.general.ecom.basics.service;

import org.fh.general.ecom.basics.model.JournalSn;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pjj
 * @since 2018-10-08
 */
public interface JournalSnService extends IService<JournalSn> {

    public String orderJournalSn();
    /**
     * 获取支付流水号
     * @return
     */
    public String payJournalSn();

    /**
     * 获取退款流水号
     * @return
     */
    public String refundJournalSn();
	
}
