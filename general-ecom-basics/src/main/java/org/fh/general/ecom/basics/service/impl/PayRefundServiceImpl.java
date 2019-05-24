package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.client.OrderClient;
import org.fh.general.ecom.basics.dao.AdminDao;
import org.fh.general.ecom.basics.dao.PayRefundDao;
import org.fh.general.ecom.basics.model.Admin;
import org.fh.general.ecom.basics.model.ElectronicsAccount;
import org.fh.general.ecom.basics.model.PayRefund;
import org.fh.general.ecom.basics.service.BankCardService;
import org.fh.general.ecom.basics.service.ElectronicsAccountService;
import org.fh.general.ecom.basics.service.PayRefundService;
import org.fh.general.ecom.basics.service.UserService;
import org.fh.general.ecom.common.dto.basics.user.UserPhoneOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.bankCard.BankCardOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.payRefund.*;
import org.fh.general.ecom.common.dto.order.order.OrderEntityOutDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-10-15
 */
@Service
public class PayRefundServiceImpl extends ServiceImpl<PayRefundDao, PayRefund> implements PayRefundService {
   @Autowired
   private UserService userService;
   @Autowired
   private ElectronicsAccountService electronicsAccountService;
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private OrderClient orderClient;

    /**
     * 添加提现申请记录
     * @param dto
     */
    @Override
    public String insertTxPayRefund(PayRefundInsertInputDTO dto) throws Exception {
        PayRefund pr=new PayRefund();
        //根据userId获取用户信息
        UserPhoneOutputDTO One = userService.findPhoneOne(dto.getUserId());
        //判断提交金额
        int i = dto.getAmount().compareTo(One.getCashAmount());
        if(i>0){
            return  "提现金额大于账户金额！";
        }
        pr.setBranch(One.getBranch());
        pr.setUserId(dto.getUserId());//用户
        pr.setAmount(dto.getAmount());//金额
        pr.setNickName(One.getNickName());//
        pr.setRealName(One.getRealName());
        pr.setPhone(One.getPhone());
        pr.setApplyTime(new Date());
        pr.setAccountNo(One.getAccount());//账户
        //获取账户信信息
        ElectronicsAccount acc = electronicsAccountService.findByUserId(dto.getUserId());
        if (null == acc) {
            return "未查到账户信息！";
        }
        if(!dto.getPayPassword().equals(acc.getPayPassword())){
            return "支付密码不正确!";
        }
        int j = dto.getAmount().compareTo(acc.getCanExtractAmount());
        if(j>0){
            return  "提现金额大于可提现金额！";
        }
        pr.setAccountId(acc.getAccountId());
        //获取银行卡信息
        BankCardOutputDTO bankCard = bankCardService.selectByPrimaryKey(dto.getBankId());
        pr.setBankCard(bankCard.getAccountNo());
        pr.setBankName(bankCard.getBankCode());
        pr.setCardName(bankCard.getCardName());
        //类型
        pr.setType("0");
        pr.setApplyRemark(dto.getApplyRemark());
        baseMapper.insert(pr);
        //电子账户减钱？总钱不变，冻结一些，可提现金额减钱
        acc.setFreezeAmount(acc.getFreezeAmount().add(dto.getAmount()));//冻结
        acc.setCanExtractAmount(acc.getCanExtractAmount().subtract(dto.getAmount()));//可提现减钱
        acc.setLastTime(System.currentTimeMillis());
        electronicsAccountService.updateById(acc);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public PayRefundListOutputDTO findPage(PayRefundListInputDTO dto){
        PayRefundListOutputDTO response=new PayRefundListOutputDTO();
        EntityWrapper<PayRefund> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        wrapper.eq("branch",dto.getBranch());
        wrapper.eq("type",dto.getType());
        if(StringUtils.isNotEmpty(dto.getAccountNo())){
            wrapper.eq("account_no",dto.getAccountNo());
        }
        if(StringUtils.isNotEmpty(dto.getTxStatus())){
            wrapper.eq("tx_status",dto.getTxStatus());
        }
        if(StringUtils.isNotEmpty(dto.getOrderNo())){
            wrapper.eq("order_no",dto.getOrderNo());
        }
        if(StringUtils.isNotEmpty(dto.getTkStatus())){
            wrapper.eq("tk_status",dto.getTkStatus());
        }
        if(StringUtils.isNotEmpty(dto.getTkType())){
            wrapper.eq("tk_type",dto.getTkType());
        }
        if(StringUtils.isNotEmpty(dto.getUserId())){
            wrapper.eq("user_id",dto.getUserId());
        }
        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("apply_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        if(StringUtils.isNotEmpty(dto.getRealName())){
            String str="(account_no like '%"+dto.getRealName()+"%' or real_name like '%"+dto.getRealName()+"%')";
            wrapper.and(str);
        }
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<PayRefund> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<PayRefundOutputDTO>  listpo=new ArrayList<PayRefundOutputDTO>();
        list.forEach((PayRefund temp) -> {
            PayRefundOutputDTO po=new PayRefundOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
    @Override
    public PayRefundOutputDTO findPayRefundById(Long id){
        PayRefund payRefund = baseMapper.selectById(id);
        if(null==payRefund){
            return null;
        }
        PayRefundOutputDTO po=new PayRefundOutputDTO();
        BeanUtils.copyProperties(payRefund,po);
        return po;
    }
    @Override
    public String updatePayRefundById(PayRefundUpdateInputDTO dto){
        PayRefund payRefund=baseMapper.selectById(dto.getId());
        Admin admin = adminDao.selectByPrimaryKey(dto.getAdminId());
        payRefund.setAdminName(admin.getRealName());
        payRefund.setAuditTime(new Date());
        payRefund.setTxStatus(dto.getTxStatus());
        payRefund.setAuditRemark(dto.getAuditRemark());
        payRefund.setTkStatus(dto.getTkStatus());
        if(StringUtils.isNotEmpty(dto.getTxStatus())&&dto.getTxStatus().equals("1")){//提现**********************

        }
        if(StringUtils.isNotEmpty(dto.getTkStatus())&&dto.getTkStatus().equals("1")){//退款**********************

        }
        baseMapper.updateById(payRefund);//修改
        return OutEnum.SUCCESS.getCode();
    }
    /**
     * 添加退款申请记录
     * @param dto
     */
    @Override
    public String insertTkPayRefund(PayRefundInsertTkInputDTO dto) throws Exception {
        PayRefund pr=new PayRefund();
        OrderEntityOutDTO order = orderClient.findEntityByOrderSnOfPay(dto.getOrderNo());
        if(order==null){
            return "订单不存在！";
        }
        //根据userId获取用户信息
        UserPhoneOutputDTO One = userService.findPhoneOne(order.getUserId());
        pr.setBranch(order.getBranch());
        pr.setUserId(order.getUserId());//用户
        pr.setAmount(order.getAllPrice());//金额
        pr.setNickName(One.getNickName());//
        pr.setRealName(One.getRealName());
        pr.setPhone(One.getPhone());
        pr.setApplyTime(new Date());
        pr.setAccountNo(One.getAccount());//账户
        //获取账户信信息
        ElectronicsAccount acc = electronicsAccountService.findByUserId(order.getUserId());
        if (null == acc) {
            return "未查到账户信息！";
        }
        pr.setAccountId(acc.getAccountId());
        //类型
        pr.setType("1");//退款
        baseMapper.insert(pr);
        return OutEnum.SUCCESS.getCode();
    }
}
