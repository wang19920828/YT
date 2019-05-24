package org.fh.general.ecom.order.controller;

import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogListInputDTO;
import org.fh.general.ecom.common.dto.order.couponsLog.CouponsLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.order.couponsLog.CouponsLogListOutPO;
import org.fh.general.ecom.common.vo.order.couponsLog.CouponsLogListVO;
import org.fh.general.ecom.order.service.CouponsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 优惠券发放记录表 前端控制器
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
@RestController
public class CouponsLogController {





    @Autowired
    private CouponsLogService couponsLogService;


    /**
     * 分页列表
     * */
    @RequestMapping("COL8005")
    public PagingVO findPage(CouponsLogListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {
            if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
                pagingVO.mustParam();
                return pagingVO;
            }
            CouponsLogListOutputDTO dtoEntity= this.couponsLogService.findPage(dto);
            List<CouponsLogListOutPO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
            List<CouponsLogListVO> listvo=new ArrayList<CouponsLogListVO>();
            list.forEach((CouponsLogListOutPO temp) -> {
                CouponsLogListVO voEn=new CouponsLogListVO();
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
     * 添加
     * */
    @RequestMapping("COL8001")
    public BaseVO addEntity(CouponsLogAddInputDTO dto) {
        BaseVO baseVO=new BaseVO();
        try {
            String code=this.couponsLogService.addEntity(dto);
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



}
