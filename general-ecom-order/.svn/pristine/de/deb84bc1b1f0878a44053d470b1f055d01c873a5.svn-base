package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeAddInputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeDetailOutputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeListInputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeListOutputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.CouponsCodeUpdateInputDTO;
import org.fh.general.ecom.common.dto.order.couponsCode.MyCouponsCodeListInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.MyGoldTicketInputDTO;
import org.fh.general.ecom.common.enumeration.order.GoldTicketEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.couponsCode.CouponsCodeListOutPO;
import org.fh.general.ecom.common.po.order.couponsCode.MyCouponsCodePO;
import org.fh.general.ecom.common.po.order.goldTicket.MyGoldOutPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.order.couponsCode.CouponsCodeDetailVO;
import org.fh.general.ecom.common.vo.order.couponsCode.CouponsCodeListVO;
import org.fh.general.ecom.order.service.CouponsService;
import org.fh.general.ecom.order.service.CouponsCodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠码表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-12
 */
@RestController
public class CouponsCodeController {


    @Autowired
    private CouponsCodeService couponsCodeService;


    /**
     * 分页列表
     * */
    @RequestMapping("CODE8005")
    public PagingVO findPage(CouponsCodeListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            CouponsCodeListOutputDTO dtoEntity= this.couponsCodeService.findPage(dto);
            List<CouponsCodeListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<CouponsCodeListVO> listvo=new ArrayList<CouponsCodeListVO>();
            list.forEach((CouponsCodeListOutPO temp) -> {
                CouponsCodeListVO voEn=new CouponsCodeListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }



    /**
     * 发放优惠券（生成优惠码）
     * */
    @RequestMapping("CODE8001")
    public BaseVO addEntity(CouponsCodeAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            if(StringUtils.isEmpty(dto.getCouponsId()) ||StringUtils.isEmpty(dto.getCouponsId())
                    || StringUtils.isEmpty(dto.getSendNum()) ||StringUtils.isEmpty(dto.getSendNum())){
                baseVO.mustParam();
                return baseVO;
            }

            String code=this.couponsCodeService.addEntity(dto);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }

    /**
     * 删除
     * */
    @RequestMapping("CODE8002")
    public BaseVO deleteEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.couponsCodeService.deleteEntityById(id);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }
    /**
     * 修改
     * */
    @RequestMapping("CODE8003")
    public BaseVO updateEntity(CouponsCodeUpdateInputDTO dto){
        BaseVO baseVO=new BaseVO();
        try {
            if(StringUtils.isEmpty(dto.getId())){
                baseVO.mustParam();
                return baseVO;
            }
            String code=this.couponsCodeService.updateEntity(dto);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.error(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }
    /**
     * 详情
     * */
    @RequestMapping("CODE8004")
    public BaseVO findEntityById(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            CouponsCodeDetailOutputDTO response= this.couponsCodeService.findEntityById(id);
            if(response==null){
                baseVO.noData();
                return baseVO;
            }
            CouponsCodeDetailVO vo=new CouponsCodeDetailVO();
            BeanUtils.copyProperties(response,vo );
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  baseVO;
    }


    /**
     * 我的优惠券=我的消费权益
     * @param dto
     * @return
     */
    @RequestMapping("CODE8006")
    public BaseVO myCouponsCodeList(MyCouponsCodeListInputDTO dto){
        BaseVO baseVO=new BaseVO();
        List<MyCouponsCodePO>  list=  this.couponsCodeService.myCouponsCodeList(dto);
        if(list!=null && list.size()>0){
            baseVO.success(list);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

}
