package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.PayRefundService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.user.payRefund.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-10-15
 */
@RestController
public class PayRefundController {
    @Autowired
    private PayRefundService payRefundService;
   /**
     * 添加提现申请记录
     * @param dto
     */
   @RequestMapping("PR000001")
    public BaseVO insertTxPayRefund (PayRefundInsertInputDTO dto){
        BaseVO baseVO = new BaseVO();
       try {
           String out = payRefundService.insertTxPayRefund(dto);
           if (!OutEnum.SUCCESS.getCode().equals(out)) {
               baseVO.warn(out);
               return baseVO;
           }
           baseVO.success();
       }catch (Exception e) {
         e.printStackTrace();
       }
        return baseVO;
    }
    /**
     * 查看提现详情
     */
    @RequestMapping("PR000002")
    public BaseVO findPayRefundById (Long id){
        BaseVO baseVO = new BaseVO();
        PayRefundOutputDTO pr= payRefundService.findPayRefundById(id);
        if(pr!=null){
            baseVO.success(pr);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     * 提现审核(退款)
     */
    @RequestMapping("PR000003")
    public BaseVO updatePayRefundById (PayRefundUpdateInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code= payRefundService.updatePayRefundById(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.warn(code);
        }
        baseVO.success();
        return baseVO;
    }
    /**
    * 分页列表
    * */
    @RequestMapping("PR000004")
    public PagingVO findPage(PayRefundListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            PayRefundListOutputDTO dtoEntity= this.payRefundService.findPage(dto);
            List<PayRefundOutputDTO> list= dtoEntity.getList();
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
     * 添加退款申请
     * @param dto
     */
    @RequestMapping("PR000005")
    public BaseVO insertRefundApply (@RequestBody PayRefundInsertTkInputDTO dto){
        BaseVO baseVO = new BaseVO();
        try {
            String out = payRefundService.insertTkPayRefund(dto);
            if (!OutEnum.SUCCESS.getCode().equals(out)) {
                baseVO.warn(out);
                return baseVO;
            }
            baseVO.success();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }
}
