package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.ElectronicsAccountService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.comm.OutCodeEntity;
import org.fh.general.ecom.common.dto.basics.user.electronicsAccount.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 电子账户基本信息表 前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-09-27
 */
@RestController
public class ElectronicsAccountController {

    @Autowired
    private ElectronicsAccountService electronicsAccountService;
    /**
     *开通电子账户（添加）
     */
    @RequestMapping("EAC00001")
    public BaseVO initAccount(ElectronicsInitAccountInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code = electronicsAccountService.initAccount(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     *  置支付密码
     */
    @RequestMapping("EAC00002")
    public BaseVO createAccountPwd(ElectronicsAccountPwdInputDTO dto){
        BaseVO baseVO = new BaseVO();
        OutCodeEntity code = electronicsAccountService.createAccountPwd(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code.getCode())){
            baseVO.error(code.getMessage());
            return  baseVO;
        }
      //  baseVO.success(code.getObj());
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改支付密码
     */
    @RequestMapping("EAC00003")
    public BaseVO updatePayPwd (ElectronicsAccountUpdatePwdInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code = electronicsAccountService.updatePayPwd(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 根据userId查电子账户信息
     */
    @RequestMapping("EAC00004")
    public ElectronicsAccountOutputDTO findAccountByUserId (@RequestBody Long userId){
        ElectronicsAccountOutputDTO out= electronicsAccountService.findAccountByUserId(userId);
        return out;
    }

    /**
     * 冻结、取消冻结
     */
    @RequestMapping("EAC00006")
    public BaseVO accountFreeze (ElectronicsAccountFreezeOutputDTO dto){
        BaseVO baseVO = new BaseVO();
        String out = electronicsAccountService.accountFreeze(dto);
        if(!OutEnum.SUCCESS.getCode().equals(out)){
            baseVO.error(out);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 账户分页查询
     */
    @RequestMapping("EAC00007")
    public PagingVO findPage(ElectronicsAccountListIntputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            ElectronicsAccountListOutputDTO dtoEntity= this.electronicsAccountService.findPage(dto);
            List<ElectronicsAccountOutputDTO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            pagingVO.success(list,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }
    /**
     * 根据userId查电子账户信息
     */
    @RequestMapping("EAC00009")
    public BaseVO findAccountUserId (Long userId){
        BaseVO baseVO = new BaseVO();
        ElectronicsAccountOutputDTO out= electronicsAccountService.findAccountByUserId(userId);
        if(out==null){
            baseVO.noData();
        }else{
            baseVO.success(out);
        }
        return baseVO;
    }
    /**
     * 忘记支付密码
     */
    @RequestMapping("EAC00010")
    public BaseVO updateForgetPayPwd (ElectronicsAccountForgetPwdInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code = electronicsAccountService.updateForgetPayPwd(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 分红往电子账号存钱
     */
    @RequestMapping("EAC00005")
    public BaseVO addMoneyToAccount (@RequestBody ElectronicsAccountRechargePayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String out = electronicsAccountService.rechargeRedPay(dto);
        if(!OutEnum.SUCCESS.getCode().equals(out)){
            baseVO.error(out);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 退款
     */
    @RequestMapping("EAC00011")
    public BaseVO refundPay (ElectronicsAccountRechargePayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String out = electronicsAccountService.refundPay(dto);
        if(!OutEnum.SUCCESS.getCode().equals(out)){
            baseVO.error(out);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * （充值*******充值记录在微信充值中记录***********）
     */
    @RequestMapping("EAC00012")
    public BaseVO topUpPay (ElectronicsAccountTopUPPayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String out = electronicsAccountService.topUpPay(dto);
        if(!OutEnum.SUCCESS.getCode().equals(out)){
            baseVO.error(out);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 提现
     */
    @RequestMapping("EAC00008")
    public BaseVO rechargeTXPay (ElectronicsAccountRechargeMinusPayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String out = electronicsAccountService.rechargeMinusPay(dto);
        if(!OutEnum.SUCCESS.getCode().equals(out)){
            baseVO.error(out);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 消费
     */
    @RequestMapping("EAC00013")
    public BaseVO rechargeXFsPay (ElectronicsAccountTXPayInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String out = electronicsAccountService.rechargeXFsPay(dto);
        if(!OutEnum.SUCCESS.getCode().equals(out)){
            baseVO.error(out);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

}
