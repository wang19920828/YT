package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.fh.general.ecom.basics.client.OrderClient;
import org.fh.general.ecom.basics.dao.WechatAcctInfoDao;
import org.fh.general.ecom.basics.model.HttpReqlog;
import org.fh.general.ecom.basics.model.TransJournal;
import org.fh.general.ecom.basics.model.WechatAcctInfo;
import org.fh.general.ecom.basics.service.*;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.pay.WeiXinSuccessInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxApplyRefundInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxJSAPIPayInputDTO;
import org.fh.general.ecom.common.dto.basics.pay.WxNativePayInputDTO;
import org.fh.general.ecom.common.dto.order.order.OrderEntityOutDTO;
import org.fh.general.ecom.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-10-11
 */
@Service
@Slf4j
public class WechatAcctInfoServiceImpl extends ServiceImpl<WechatAcctInfoDao, WechatAcctInfo> implements WechatAcctInfoService {
    /**
     * 交易类型
     */
    private String transType = "wechatPay";

    /**
     * 支付类型：1-微信支付
     */
    private final static String PAY_TYPE_WECHAT = "1";

    /**
     * 交易类型 1-购买
     */
    private final static String TRANS_TYPE_BUY = "1";

    /**
     * 交易类型 4-退款
     */
    private final static String TRANS_TYPE_REFUND = "4";

    /**
     * 交易状态 0101-支付中
     */
    private final static String TRANS_STUTS_PAYMENT = "0101";

    /**
     * 交易状态 0202-支付成功
     */
    private final static String TRANS_STUTS_SUCCESS = "0202";

    /**
     * 交易状态 0303-支付失败
     */
    private final static String TRANS_STUTS_FAIL = "0303";

    /**
     * 订单状态 1-正常
     */
    private final static String JOURNAL_STUTS_NORMAL = "1";

    /**
     * 微信支付状态 SUCCESS -成功
     */
    private final static String WECHAT_RESULT_SUCCESS = "SUCCESS";
    /**
     * 订单时间
     */
    private final static String ORDER_TIMEOUT = "order_timeout";
    public final static String TIMEOUT_VALUE="timeout_value";
    /**
     * 统一微信下单地址
     */
    @Value("${unified_order_url}")
    private String unifiedOrderURL;
    /**
     * 退款地址
     */
    @Value("${refund_url}")
    private String refundURL;
    /**
     * 终端ip
     */
    /*
     * @Value("${spbill_create_ip}") private String spbill_create_ip;
     */

    private final static String REQUESTCHAR_SET = "UTF-8";
    private final static String RETURN_CODE_SUCCESS = "SUCCESS";

    @Autowired
    private JournalSnService journalSnService;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private TransJournalService transJournalService;

    @Autowired
    private HttpReqlogService httpReqlogService;

    @Autowired
    private CmsParamService cmsParamService;


    /**
     * 微信公众号支付
     */
      @Override
    public OutCodeEntity wxJSAPIPay(WxJSAPIPayInputDTO dto) {
        log.info("============================微信公众号支付service开始=========================");
        OutCodeEntity out=new OutCodeEntity();
        SortedMap<String, Object> signParams =new TreeMap<>();
        HttpReqlog httpReqLog = new HttpReqlog();
        try {
            // 查询订单相关信息
            String journalSn = journalSnService.payJournalSn();// 交易流水号
            String orderSn = dto.getOrderSn();// 订单号
           OrderEntityOutDTO order = this.orderClient.findEntityByOrderSnOfPay(orderSn);
            if (order == null) {
                out.setCode("222222");out.setMessage("订单不存在");
                return out;
            }
            // 根据渠道号查询微信支付相关配置
            WechatAcctInfo chatAcct = this.findByChannelNo(dto.getChannelNo());
            if (chatAcct != null) {
                // 传参
                signParams.put("appid", chatAcct.getAppId());// 公众账号ID
                signParams.put("mch_id", chatAcct.getMchId());// 商户号
                signParams.put("nonce_str", Sign.getRandomString(32));// 随机字符串
                signParams.put("attach", order.getUserId().toString());// 附加数据
                signParams.put("notify_url", chatAcct.getNotifyUrl());// 通知地址
                signParams.put("out_trade_no", journalSn);// 商户订单号
                signParams.put("total_fee",ObjectUtil.convertCent(order.getAllPrice())+"");// 标价金额:分
                signParams.put("spbill_create_ip", "");// 终端IP
                signParams.put("trade_type", "JSAPI");// 交易类型
                signParams.put("openid", dto.getOpenid());// 用户标识
                signParams.put("body", "微信公众号支付");// 商品描述
                // 记录交易流水
                addPayJournal(journalSn, order, TRANS_TYPE_BUY, PAY_TYPE_WECHAT,dto.getBranch());
                // 发送微信请求
                String strResponse = getResultMsg(chatAcct.getWechatKey(),signParams, unifiedOrderURL);
                // 处理微信返回结果
                out = uniformOrderResultHandle(strResponse,chatAcct.getWechatKey());
                // 记录请求、响应日志
                httpReqLog.setReqParam(JSONObject.fromObject(signParams).toString());
                httpReqLog.setReqUrl(unifiedOrderURL);
                httpReqLog.setKeyWord(orderSn);
                httpReqLog.setTransType(transType);
                httpReqLog.setExt1(journalSn);
                httpReqLog.setExt2("微信公众号支付");
                httpReqLog.setResResult(strResponse);

            }
        } catch (Exception e) {
            out.setCode("222222");out.setMessage("交易失败");
            e.printStackTrace();
        }
        httpReqLog.setCreateTime(System.currentTimeMillis());
        httpReqlogService.insert(httpReqLog);
        log.info("============================微信公众号支付service结束=========================");
        return out;
    }

    /**
     * 微信扫码支付
     */
    @Override
    public OutCodeEntity wxNativePay(WxNativePayInputDTO dto) {
        log.info("============================微信扫码支付service开始=========================");
        OutCodeEntity out = new OutCodeEntity();
        SortedMap<String, Object> signParams =new TreeMap<>();
        HttpReqlog httpReqLog = new HttpReqlog();
        try {
            // 查询订单相关信息
            String journalSn = journalSnService.payJournalSn();// 交易流水号
            String orderSn = dto.getOrderSn();// 订单号
            OrderEntityOutDTO order = orderClient.findEntityByOrderSnOfPay(orderSn);
            if (order == null) {
                out.setCode("222222");out.setMessage("编号为："+orderSn+"的订单不存在");
                return out;
            }
            if(order.getOrderStatus().equals("5")){//1-正常 2-预约未申购 3-认购失败 4-冷静期退出  5-已失效  6-认购成功
                out.setCode("222222");out.setMessage("编号为："+orderSn+"的订单已取消！");
                return out;
            }
            if(order.getIsDel().equals("1")){//是否删除（0-否 1-是）
                out.setCode("222222");out.setMessage("编号为："+orderSn+"的订单已删除！");
                return out;
            }
            //失效时间
            long endTime=getTimeoutValue(order.getAddTime().getTime());
            if(System.currentTimeMillis()>endTime){
                out.setCode("222222");out.setMessage("该订单已超时，请重新下单!");
                return out;
            }
            // 根据渠道号查询微信支付相关配置
            WechatAcctInfo chatAcct = this.findByChannelNo(dto.getChannelNo());
            if (chatAcct != null) {
                // 传参
                signParams.put("appid", chatAcct.getAppId());// 公众账号ID
                signParams.put("mch_id", chatAcct.getMchId());// 商户号
                signParams.put("nonce_str", Sign.getRandomString(32));// 随机字符串
                signParams.put("attach", order.getUserId().toString());// 附加数据
                signParams.put("notify_url", chatAcct.getNotifyUrl());// 通知地址
                signParams.put("out_trade_no", journalSn);// 商户订单号
                signParams.put("total_fee",ObjectUtil.convertCent(order.getAllPrice()) + "");// 标价金额:分
                signParams.put("spbill_create_ip", "");// 终端IP
                signParams.put("trade_type", "NATIVE");// 交易类型
                signParams.put("product_id", order.getOrderId().toString());// 商品ID
                signParams.put("body", "微信扫码支付");// 商品描述
                signParams.put("time_expire", DateUtils.formatDateForWx(endTime)+"");//订单失效时间
                // 记录交易流水
                addPayJournal(journalSn, order, TRANS_TYPE_BUY, PAY_TYPE_WECHAT,dto.getBranch());
                // 发送微信请求
                String strResponse = getResultMsg(chatAcct.getWechatKey(),signParams, unifiedOrderURL);
                // 处理微信返回结果
                out = uniformOrderResultHandle(strResponse,chatAcct.getWechatKey());
                // 记录请求、响应日志

                httpReqLog.setReqParam(JSONObject.fromObject(signParams).toString());
                httpReqLog.setReqUrl(unifiedOrderURL);
                httpReqLog.setKeyWord(orderSn);
                httpReqLog.setTransType(transType);
                httpReqLog.setExt1(journalSn);
                httpReqLog.setExt2("微信扫码支付");
                httpReqLog.setResResult(strResponse);

            }
        } catch (Exception e) {
            out.setCode("222222");out.setMessage("交易失败");
            e.printStackTrace();
            e.printStackTrace();
        }
        httpReqlogService.insert(httpReqLog);
        log.info("============================微信扫码支付service结束=========================");
        return out;
    }

   public long getTimeoutValue(Long createTime){
        Map<String,String> order_timeout=cmsParamService.selectByParamName(ORDER_TIMEOUT);
        String timeout_value=order_timeout.get(TIMEOUT_VALUE);
        int i = Integer.valueOf(timeout_value);
        log.info("=========getTimeoutValue的i"+i);
        long endTime=createTime+(i * 60 * 1000);
        log.info("=========getTimeoutValue的endTime"+endTime);
        return endTime;

    }

    /**
     * 微信支付成功通知
     */
    @Override
    public OutCodeEntity weiXinSuccess(WeiXinSuccessInputDTO dto) {
        OutCodeEntity out=new OutCodeEntity();
        HttpReqlog httpReqLog = new HttpReqlog();
        log.info("微信支付成功入参：" + JSONObject.fromObject(dto));
        try {
            httpReqLog.setReqParam(JSONObject.fromObject(dto).toString());

//            String out_trade_no = params.get("out_trade_no").toString();// 商户订单号
//            String transaction_id = params.get("transaction_id").toString();// 微信支付订单号
//            String total_fee = params.get("total_fee").toString();// 订单总金额，单位为分

            String out_trade_no = dto.getOut_trade_no();// 商户订单号
            String transaction_id = dto.getTransaction_id();// 微信支付订单号
            String total_fee = Integer.toString(dto.getTotal_fee());// 订单总金额，单位为分


            TransJournal journal = transJournalService.findJournal(out_trade_no);
            if (journal != null) {
                if (!TRANS_STUTS_SUCCESS.equals(journal.getTransStuts())) {
                    journal.setOverTime(System.currentTimeMillis());
                    if (WECHAT_RESULT_SUCCESS.equals(dto.getResult_code())&& WECHAT_RESULT_SUCCESS.equals(dto.getReturn_code())) {
                        journal.setChannelJournal(transaction_id);
                        journal.setTransStuts(TRANS_STUTS_SUCCESS);
                    } else {
                        String err_code = dto.getErr_code();
                        String err_code_des = dto.getErr_code_des();
                        journal.setRemark(err_code + " : " + err_code_des);
                        journal.setTransStuts(TRANS_STUTS_FAIL);
                    }
                    this.transJournalService.mdifyJournal(journal);
                    // 消费
                    String orderSn = journal.getOrderSn();
                    OrderEntityOutDTO order = this.orderClient.findEntityByOrderSnOfPay(orderSn);

                    //修改订单支付状态********************************************
//                    Map<String, String> m = new HashMap<String, String>();
//                    m.put("orderId", order.getOrderId().toString());
//                    m.put("paySum", ObjectUtil.convertYuan(total_fee).toString());
//                    m.put("sendEmailType", "0");//发邮件
//                    m.put("payType", journal.getPayType());
//                    m.put("tradeNo", transaction_id);
//                    this.orderService.payOrder(m);
                    // 记录请求、响应日志
                    httpReqLog.setReqUrl(unifiedOrderURL);
                    httpReqLog.setKeyWord(orderSn);
                    httpReqLog.setTransType(transType);
                    httpReqLog.setExt1(out_trade_no);
                    httpReqLog.setExt2("微信支付成功回调");
                }
                out.setCode("000000");out.setMessage("成功！");
            }
        } catch (Exception e) {
            out.setCode("222222");out.setMessage("微信支付回调出错了！");
            log.error("微信支付回调出错了！", e);
            e.printStackTrace();
        }
        // 返回结果
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("return_code", "<![CDATA[SUCCESS]]>");
        map.put("return_msg", "<![CDATA[OK]]>");
        out.setObj(map);
        httpReqLog.setResResult(JSONObject.fromObject(map).toString());
        httpReqlogService.insert(httpReqLog);
        return out;
    }

    /**
     * 申请退款
     */
    @Override
    public OutCodeEntity applyRefund(WxApplyRefundInputDTO dto) {
        OutCodeEntity out=new OutCodeEntity();
        HttpReqlog httpReqLog = new HttpReqlog();
        SortedMap<String, Object> signParams =new TreeMap<>();
        try {
            WechatAcctInfo chatAcct = this.findByChannelNo(dto.getChannelNo());
            if (chatAcct != null) {
                String orderSn = dto.getOrderSn();
                String refundAmount = dto.getRefundAmount().toString();// 退款金额
                OrderEntityOutDTO order = this.orderClient.findEntityByOrderSnOfPay(orderSn);
                if (order == null) {
                    out.setCode("222222");out.setMessage("订单不存在");
                    return out;
                }
                //判断退款金额是否大于订单支付金额
                long reFundamountFen=ObjectUtil.convertCent(new BigDecimal(refundAmount));//退款金额
                long orderNeedSum =ObjectUtil.convertCent(order.getAllPrice());//原订单金额
                if(reFundamountFen > orderNeedSum){
                    out.setCode("222222");out.setMessage("退款金额不能大于订单支付金额！");
                    return out;
                }
                //查询该订单的交易流水
                Map<String, Object> queryParams=new HashMap<String, Object>();
                queryParams.put("order_sn", orderSn);
                queryParams.put("trans_stuts", TRANS_STUTS_SUCCESS);
                queryParams.put("trans_type", "1");
                TransJournal journal=transJournalService.findByParams(queryParams);
                if (journal == null) {
                    out.setCode("222222");out.setMessage("交易订单不存在 ！");
                    return out;
                }

                String journalSn = journalSnService.payJournalSn();
                //添加退款交易记录
                TransJournal transJoutnal=addRefundJournal(journalSn, order, refundAmount,TRANS_TYPE_REFUND, PAY_TYPE_WECHAT, dto.getBranch() );
                //传参
                signParams.put("appid", chatAcct.getAppId());
                signParams.put("mch_id", chatAcct.getMchId());
                signParams.put("out_refund_no", transJoutnal.getJournalNo());
                signParams.put("transaction_id", journal.getChannelJournal());
                signParams.put("nonce_str", Sign.getRandomString(32));
                signParams.put("total_fee",ObjectUtil.convertCent(order.getAllPrice()) + "");
                signParams.put("refund_fee",ObjectUtil.convertCent(new BigDecimal(refundAmount))+ "");
                // 记录请求、响应日志
                httpReqLog.setReqParam(JSONObject.fromObject(signParams).toString());
                httpReqLog.setReqUrl(refundURL);
                httpReqLog.setKeyWord(orderSn);
                httpReqLog.setTransType(transType);
                httpReqLog.setExt1(journalSn);
                httpReqLog.setExt2("微信退款");
                //退款请求
                String strResponse = getResultMsg(chatAcct.getWechatKey(),signParams, refundURL, chatAcct.getCertificateName(),chatAcct.getMchId());
                log.info("微信退款返回:" + strResponse);
                httpReqLog.setResResult(strResponse);
                //处理返回结果
                Map<String, Object> resultMap = XmlUtils.conventMap(strResponse);
                if (RETURN_CODE_SUCCESS.equals(resultMap.get("return_code"))
                        && RETURN_CODE_SUCCESS.equals(resultMap
                        .get("result_code"))) {
                    transJoutnal.setChannelJournal(resultMap.get("transaction_id").toString());
                    transJoutnal.setTransStuts(TRANS_STUTS_SUCCESS);
                    transJoutnal.setRefundAmt(new BigDecimal(refundAmount));
                    out.setCode("000000");out.setMessage("成功！");
                    out.setObj(resultMap);
                } else {
                    String err_code_des = resultMap.get("err_code_des").toString();
                    String err_code = resultMap.get("err_code").toString();
                    out.setCode(err_code);out.setMessage(err_code_des);
                    journal.setRemark(err_code+":"+err_code_des);
                    journal.setTransStuts(TRANS_STUTS_FAIL);
                }
                transJournalService.mdifyJournal(transJoutnal);
            }
        } catch (Exception e) {
            log.error("申请退款接口处理异常",e);
            e.printStackTrace();
        }
        httpReqlogService.insert(httpReqLog);
        return out;
    }

    /**
     * 添加退款流水
     *
     * @param journalSn
     * @param order
     * @param refundAmount
     * @param refundAmount
     * @param transType
     * @param payType
     * @return
     */
    private TransJournal addRefundJournal(String journalSn, OrderEntityOutDTO order, String refundAmount,String transType, String payType, String branch ) {
        TransJournal transJournal = new TransJournal();
        try {
            transJournal.setJournalNo(journalSn);
            transJournal.setOrderId(order.getOrderId());
            transJournal.setOrderSn(order.getOrderSn());
            transJournal.setPayType(payType);
            transJournal.setTransType(transType);
            transJournal.setTransStuts(TRANS_STUTS_PAYMENT);// 支付中
            transJournal.setState(JOURNAL_STUTS_NORMAL);//正常
            transJournal.setTransAmt((order.getAllPrice()));
            transJournal.setRefundAmt(new BigDecimal("0.00"));//退款成功以后增加退款金额
            transJournal.setScoreAmt(BigDecimal.ZERO);
            transJournal.setYhqAmt(BigDecimal.ZERO);
            transJournal.setChannelNo(branch);
            transJournal.setOrderJournal(order.getOrderSn());
            transJournal.setBranch(branch);
            transJournal.setUserId(order.getUserId());
            this.transJournalService.saveTransJournal(transJournal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transJournal;

    }

    /**
     * 添加下单交易流水
     *
     * @param journalSn
     * @param order
     * @param transType
     * @param payType
     * @throws Exception
     */
    private void addPayJournal(String journalSn, OrderEntityOutDTO order, String transType, String payType, String branch) {
        try {
            TransJournal transJournal = new TransJournal();
            transJournal.setJournalNo(journalSn);
            transJournal.setOrderId(order.getOrderId());
            transJournal.setOrderSn(order.getOrderSn());
            transJournal.setPayType(payType);
            transJournal.setTransType(transType);
            transJournal.setTransStuts(TRANS_STUTS_PAYMENT);// 支付中
            transJournal.setState(JOURNAL_STUTS_NORMAL);// 正常
            transJournal.setTransAmt(order.getAllPrice());
            transJournal.setRefundAmt(BigDecimal.ZERO);
            transJournal.setScoreAmt(BigDecimal.ZERO);
            transJournal.setYhqAmt(BigDecimal.ZERO);
            transJournal.setChannelNo(branch);
            transJournal.setOrderJournal(order.getOrderSn());
            transJournal.setBranch(branch);
            transJournal.setUserId(order.getUserId());
            this.transJournalService.saveTransJournal(transJournal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下单请求
     *
     * @param params
     * @param url
     * @return
     */
    public String getResultMsg(String key, SortedMap<String, Object> params,
                               String url) {
        params.put("sign", Sign.createSign(params, key));// 签名
        String requestMsg = Sign.mapToXml(params);// 将请求参数转为xml
        String strResponse = HttpUtils.sendXMLPost(url, requestMsg,
                REQUESTCHAR_SET);
        return strResponse;
    }

    /**
     * 退款请求
     *
     * @param key
     * @param params
     * @param url
     * @param certificateName
     * @param mchId
     * @return
     */
    public String getResultMsg(String key, SortedMap<String, Object> params,
                               String url, String certificateName, String mchId) {
        params.put("sign", Sign.createSign(params, key));// 签名
        String requestMsg = Sign.mapToXml(params);
        String basePath = System.getProperty("springmvc.root");
        String sllURL = basePath + "/SSL/" + certificateName;
        String strResponse = "";
        try {
            strResponse = HttpUtils.sendXMLSLLPost(url, requestMsg,
                    REQUESTCHAR_SET, sllURL, mchId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResponse;
    }

    /**
     * 处理下单返回结果
     *
     * @param strResponse
     * @param key
     * @return
     */
    public OutCodeEntity uniformOrderResultHandle(String strResponse, String key) {
        OutCodeEntity out=new OutCodeEntity();//返回内容
        Map<String, Object> resultMap = XmlUtils.conventMap(strResponse);
        SortedMap<String, Object> signMap = new TreeMap<String, Object>();
        if (RETURN_CODE_SUCCESS.equals(resultMap.get("return_code")) && RETURN_CODE_SUCCESS.equals(resultMap.get("result_code"))) {
            out.setCode("000000");out.setMessage("成功！");
            long timestamp = System.currentTimeMillis() / 1000;
            String appId = (String) resultMap.get("appid");//公众账号ID
            String mch_id = (String) resultMap.get("mch_id");//商户号
            String nonceStr = (String) resultMap.get("nonce_str");// 随机字符串
            String trade_type = (String) resultMap.get("trade_type");// 交易类型
            String prepay_id = (String) resultMap.get("prepay_id");// 预支付交易会话标识
            String code_url = (String) resultMap.get("code_url");// 二维码链接
            String time_expire = (String) resultMap.get("time_expire");// 订单失效时间
            if ("JSAPI".equalsIgnoreCase(trade_type)) {
                signMap.put("appId", appId);
                signMap.put("package", "prepay_id=" + prepay_id);
                signMap.put("timeStamp", String.valueOf(timestamp));
                signMap.put("nonceStr", nonceStr);
                signMap.put("signType", "MD5");//签名方式
            } else if("NATIVE".equalsIgnoreCase(trade_type)){
                signMap.put("prepayid", prepay_id);
                signMap.put("nonceStr", nonceStr);
                signMap.put("codeUrl", code_url);
                signMap.put("timeExpire", time_expire);
            }
            String paySign = Sign.createSign(signMap, key);
            resultMap.clear();
            resultMap.putAll(signMap);
            resultMap.put("paySign", paySign);
            // result.setResult(resultMap);
            out.setObj(resultMap);
        } else {
            // result.setError(resultMap.get("return_msg").toString());
            out.setCode("222222");out.setMessage(resultMap.get("return_msg").toString());
        }
        return out;
    }
    @Override
    public WechatAcctInfo findByChannelNo(String channelNo) throws Exception {
        return this. baseMapper.findByChannelNo(channelNo);
    }

    /**
     * 微信充值服务
     */
   /* @Override
    public OutCodeEntity wechatReharge(WxRechargeInputDTO dto) throws Exception {
        log.info("============================微信充值service开始=========================");
        OutCodeEntity out = new OutCodeEntity();
        SortedMap<String, Object> signParams =new TreeMap<>();
        WechatAcctInfo chatAcct = this.findByChannelNo(dto.getChannelNo());
        if (chatAcct == null) {
            out.setCode("222222");out.setMessage("未查询到平台" + dto.getChannelNo() + " 的微信信息");
            return out;
        }
        signParams.put("appid", chatAcct.getAppId());
        signParams.put("mch_id", chatAcct.getMchId());
        String attach = String.valueOf(dto.getUserId());
        if (StringUtils.isNotEmpty(dto.getPhone())) {
            attach = attach + "," + dto.getPhone();
        }
        signParams.put("attach", attach);
        String journalSn = journalSnService.payJournalSn();
        signParams.put("out_trade_no", journalSn);
        signParams.put("notify_url", getNotifyUrl(chatAcct.getNotifyUrl(), "wechatRecharge"));

            if (StringUtils.isEmpty(dto.getTotal_fee())) {
                out.setCode("222222");out.setMessage("总金额为空！");
            } else {
                if (0 == Integer.valueOf(dto.getTotal_fee())) {
                    out.setCode("222222");out.setMessage("总金额必须大于0！");
                } else {
                    BigDecimal yuan = ObjectUtils.convertYuan(dto.getTotal_fee());
                    //记录订单流水
                    addPayJournal(null, journalSn, null, yuan, new BigDecimal("0.00"), new BigDecimal("0.00"), PAY_TYPE_WECHAT, TRANS_TYPE_RECHARGE, branch, Integer.valueOf(channel), accountType, type, null, shopSn, userId);
                    String strResponse = getResultMsg(acct.getWechatKey(), signParams, unifiedOrderURL);
                    result = uniformOrderResultHandle(strResponse, acct.getWechatKey());
                }
            }

        logger.info("result：" + JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        logger.info("============================微信充值service结束=========================");
        return result;
    }

    //拼装回调地址
    private String getNotifyUrl(String url, String path) {
        url = url.trim();
        String midStr = url.endsWith("/") ? "" : "/";

        return url + midStr + "org/notice/" + path + ".do";
    }*/


}
