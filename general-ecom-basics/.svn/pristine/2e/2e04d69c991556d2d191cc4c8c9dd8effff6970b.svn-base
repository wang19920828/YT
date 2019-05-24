package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.WechatAcctInfo;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.pay.WeiXinSuccessInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxApplyRefundInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxJSAPIPayInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxNativePayInputDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
public interface WechatAcctInfoService extends IService<WechatAcctInfo> {
    /**
     * 微信公共号信息
     * @param channelNo
     * @return
     * @throws Exception
     */
    public WechatAcctInfo findByChannelNo(String channelNo)throws Exception;

    /**
     * 微信公众号支付
     * @return
     */
    public OutCodeEntity wxJSAPIPay(WxJSAPIPayInputDTO dto);

   /**
     * 微信扫码支付
     * @return
     */
    public OutCodeEntity wxNativePay(WxNativePayInputDTO dto);

    /**
     * 微信支付成功回调
     * @return
     */
    public OutCodeEntity weiXinSuccess(WeiXinSuccessInputDTO dto);

    /***
     *退款
     * @return
     */
    public OutCodeEntity applyRefund(WxApplyRefundInputDTO dto);
}
