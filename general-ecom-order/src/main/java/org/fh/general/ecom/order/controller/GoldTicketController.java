package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketAddInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketListInputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.GoldTicketListOutputDTO;
import org.fh.general.ecom.common.dto.order.goldTicket.MyGoldTicketInputDTO;
import org.fh.general.ecom.common.dto.order.order.OrderUpdateInputDTO;
import org.fh.general.ecom.common.enumeration.order.GoldTicketEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.goldTicket.GoldTicketListOutPO;
import org.fh.general.ecom.common.po.order.goldTicket.MyGoldOutPO;
import org.fh.general.ecom.common.utils.DateUtils;
import org.fh.general.ecom.common.vo.order.goldTicket.GoldTicketListVO;
import org.fh.general.ecom.order.model.Order;
import org.fh.general.ecom.order.service.GoldTicketService;
import org.fh.general.ecom.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 代金券表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class GoldTicketController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private GoldTicketService goldTicketService;


    /**
     * 分页列表
     * */
    @RequestMapping("GOL8005")
    public PagingVO findPage(GoldTicketListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            GoldTicketListOutputDTO dtoEntity= this.goldTicketService.findPage(dto);
            List<GoldTicketListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<GoldTicketListVO> listvo=new ArrayList<GoldTicketListVO>();
            list.forEach((GoldTicketListOutPO temp) -> {
                GoldTicketListVO voEn=new GoldTicketListVO();
                BeanUtils.copyProperties(temp,voEn);
                voEn.setDelayTime(this.goldTicketService.getDelayTime(temp.getOffTime()));
                listvo.add(voEn);
            });

            pagingVO.success(listvo,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }



    /**
     * 添加
     * */
    @RequestMapping("GOL8001")
    public BaseVO addEntity(GoldTicketAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.goldTicketService.addEntity(dto);
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
     * 延期
     * @param id
     * @return
     */
    @RequestMapping("GOL8002")
    public  BaseVO delay(Long id){
        BaseVO baseVO=new BaseVO();
        try {
            if(StringUtils.isEmpty(id) ||StringUtils.isEmpty(id) ){
                baseVO.mustParam();
                return baseVO;
            }
            String code=this.goldTicketService.delay(id);
            if(!OutEnum.SUCCESS.getCode().equals(code)){
                baseVO.setBusAlert(OutEnum.FAIL.getMessage());
                return  baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            baseVO.exception();
            e.printStackTrace();
        }
        return  baseVO;
    }


    /**
     * 我的代金券
     * @param dto
     * @return
     */
    @RequestMapping("GOL8003")
    public BaseVO findMyGoldList(MyGoldTicketInputDTO dto){
        BaseVO baseVO=new BaseVO();
        List<MyGoldOutPO>  list=  this.goldTicketService.findMyGoldList(dto);
        if(list!=null && list.size()>0){
            baseVO.success(list);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }




}
