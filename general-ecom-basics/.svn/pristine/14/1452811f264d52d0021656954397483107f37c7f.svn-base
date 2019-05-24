package org.fh.general.ecom.basics.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.basics.service.WechatAcctInfoService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.pay.WeiXinSuccessInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxApplyRefundInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxJSAPIPayInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxNativePayInputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@RestController
@Slf4j
public class WechatAcctInfoController {
	  @Autowired
    private WechatAcctInfoService wechatAcctInfoService;

    /**
     * 公众号支付
     *
     * @return
     * @deprecated:应用场景 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
     * 返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调
     * 起支付。
     */
    @SuppressWarnings("unchecked")
    public BaseVO wxUnifiedOrder(WxJSAPIPayInputDTO dto) {
        log.info("===================  微信统一下单功能 start ===================");
        BaseVO baseVO = new BaseVO();
        try{
            OutCodeEntity out = this.wechatAcctInfoService.wxJSAPIPay(dto);
            if(!OutEnum.SUCCESS.getCode().equals(out.getCode())){
                baseVO.warn(out.getMessage());
                return  baseVO;
            }else{
                baseVO.success(out.getObj());
            }
        } catch (Exception e) {
            baseVO.error("");
            e.printStackTrace();
        }
        log.info("===================  微信统一下单功能 end ===================");
        return baseVO;

    }

    /**
     * 扫码支付
     * @return
     * @deprecated:应用场景 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，
     * 返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调
     * 起支付。
     */
    @SuppressWarnings("unchecked")
    public BaseVO wxNativePay(WxNativePayInputDTO dto) {
        log.info("===================  微信扫码支付 start ===================");
        BaseVO baseVO = new BaseVO();
        try{
            OutCodeEntity out = this.wechatAcctInfoService.wxNativePay(dto);
            if(!OutEnum.SUCCESS.getCode().equals(out.getCode())){
                baseVO.warn(out.getMessage());
                return  baseVO;
            }else{
                baseVO.success(out.getObj());
            }
        } catch (Exception e) {
            baseVO.error("");
            e.printStackTrace();
        }
        log.info("===================  微信扫码支付 end ===================");
        return baseVO;

    }

    /**
     * 微信支付成功回调
     *
     * @return
     * @deprecated:应用场景
     */
    public BaseVO weiXinSuccess(WeiXinSuccessInputDTO dto) {
        log.info("===================  微信支付成功回调 start ===================");
        BaseVO baseVO = new BaseVO();
        try{
            //更改订单状态  签名验证,并校验返回的订单金额是否与商户侧的订单金额一致
            OutCodeEntity out = this.wechatAcctInfoService.weiXinSuccess(dto);
            if(!OutEnum.SUCCESS.getCode().equals(out.getCode())){
                baseVO.warn(out.getMessage());
                return  baseVO;
            }else{
                baseVO.success(out.getObj());
            }
        } catch (Exception e) {
            baseVO.error("");
            e.printStackTrace();
        }
        log.info("result：" + JSON.toJSONStringWithDateFormat(baseVO, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        log.info("===================  微信支付成功回调 end ===================");
        return baseVO;

    }

    /**
     * 申请退款
     *
     * @return
     */
   @SuppressWarnings("unchecked")
    public BaseVO applyRefund(WxApplyRefundInputDTO dto) {
        log.info("===================   微信申请退款功能 start ===================");
        BaseVO baseVO = new BaseVO();
        try{
            //公众号信息
            OutCodeEntity out = this.wechatAcctInfoService.applyRefund(dto);
            if(!OutEnum.SUCCESS.getCode().equals(out.getCode())){
                baseVO.warn(out.getMessage());
                return  baseVO;
            }else{
                baseVO.success(out.getObj());
            }
        } catch (Exception e) {
            baseVO.error("");
            e.printStackTrace();
        }
        log.info("===================   微信申请退款功能 end ===================");
        return baseVO;
    }
    /**
     * 微信充值功能
     * @return
     */
  /*  public BaseVO recharge(WxRechargeInputDTO dto) {
        log.info("===================   微信充值功能 start ===================");
        BaseVO baseVO = new BaseVO();
        //获取渠道号
        String channel = dto.getChannel();
        String branch = dto.getBranch();
        Object accountTypeObj = dto.getAccountType();
        if (StringUtils.isEmpty(branch)) {
            baseVO.error("平台为空");
            return baseVO;
        }
        Integer accountType = Integer.valueOf(accountTypeObj.toString());
        Long userId = dto.getUserId();
        if (StringUtils.isEmpty(accountType)) {
            baseVO.error("账户类型不能为空");
            return baseVO;
        }
        //用户Id
        if (StringUtils.isEmpty(userId)) {
            baseVO.error("用户ID不能为空");
            return baseVO;
        }
        if (0 >= Integer.valueOf(dto.getTotal_fee())) {
            baseVO.error("充值金额必须大于0!");
            return baseVO;
        }
        //通过渠道号获取公众账号信息
        try {
           // SortedMap<String, Object> signParams =new TreeMap<>();
           // SortedMap<String, Object> signParams = ObjectUtils.convertBean(request);
            result = wechatAcctInfoService.wechatReharge(dto);
        } catch (Exception e) {
            result.setError();
            log.error("微信充值出错了", e);
        }
        log.info("result：" + JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        log.info("===================   微信充值功能 end ===================");
        return result;
    }
*/

}
