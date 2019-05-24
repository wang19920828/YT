package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.model.AdminFunction;
import org.fh.general.ecom.basics.service.AdminFunctionService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.adminFunction.*;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogAddInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListInputDTO;
import org.fh.general.ecom.common.dto.order.orderLog.OrderLogListOutputDTO;
import org.fh.general.ecom.common.enumeration.basics.FunctionEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminFunction.FunctionListOutputPO;
import org.fh.general.ecom.common.po.order.orderLog.OrderLogListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.adminFunction.FunctionDetailVO;
import org.fh.general.ecom.common.vo.basics.adminFunction.FunctionListVO;
import org.fh.general.ecom.common.vo.order.orderLog.OrderLogDetailVO;
import org.fh.general.ecom.common.vo.order.orderLog.OrderLogListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能菜单 前端控制器
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@RestController
public class AdminFunctionController {

    @Autowired
    private AdminFunctionService adminFunctionService;

    /**
     * 查询分页
     *
     * @param dto
     * @return
     */
    @RequestMapping("FUNC1001")
    public PagingVO findPage(FunctionListInputDTO dto) {
        PagingVO pagingVO = new PagingVO();
        try {
            if (StringUtils.isEmpty(dto.getCurrentPageNum()) || StringUtils.isEmpty(dto.getPageCount())) {
                pagingVO.mustParam();
                return pagingVO;
            }
            FunctionListOutputDTO dtoEntity = this.adminFunctionService.findPage(dto);
            List<FunctionListOutputPO> list = dtoEntity.getList();
            if (list == null || list.size() == 0) {
                pagingVO.noData();
                return pagingVO;
            }
            List<FunctionListVO> listvo = new ArrayList<FunctionListVO>();
            list.forEach((FunctionListOutputPO temp) -> {
                FunctionListVO voEn = new FunctionListVO();
                BeanUtils.copyProperties(temp, voEn);
                listvo.add(voEn);
            });

            pagingVO.success(listvo, dtoEntity.getPageInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pagingVO;
    }

    /**
     * 添加
     */
    @RequestMapping("FUNC1002")
    public BaseVO addEntity(FunctionAddInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminFunctionService.addEntity(dto);
            if (!FunctionEnum.FunctionAdd.SUCCESS.getValue().equals(code)) {
                baseVO.error(FunctionEnum.FunctionAdd.codeOf(code).getName());
                return baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
            baseVO.exception(e.getMessage());
        }
        return baseVO;
    }

    /**
     * 删除
     */
    @RequestMapping("FUNC1003")
    public BaseVO deleteEntityById(String ids) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminFunctionService.deleteEntityByIds(ids);
            if (!OutEnum.SUCCESS.getCode().equals(code)) {
                baseVO.error(OutEnum.FAIL.getMessage());
                return baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 修改
     */
    @RequestMapping("FUNC1004")
    public BaseVO updateEntity(FunctionUpdateInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            if (StringUtils.isEmpty(dto.getFunModuleId())) {
                baseVO.mustParam();
                return baseVO;
            }
            String code = this.adminFunctionService.updateEntity(dto);
            if (!FunctionEnum.FunctionAdd.SUCCESS.getValue().equals(code)) {
                baseVO.error(FunctionEnum.FunctionAdd.codeOf(code).getName());
                return baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 详情
     */
    @RequestMapping("FUNC1005")
    public BaseVO findEntityById(Long id) {
        BaseVO baseVO = new BaseVO();
        try {
            FunctionDetailOutputDTO response = this.adminFunctionService.findEntityById(id);
            if (response == null) {
                baseVO.noData();
                return baseVO;
            }
            FunctionDetailVO vo = new FunctionDetailVO();
            BeanUtils.copyProperties(response, vo);
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 启用/禁用
     *
     * @param ids
     * @param isDisabled
     * @return
     */
    @RequestMapping("FUNC1006")
    public BaseVO updateStatus(String ids, String isDisabled) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminFunctionService.updateStatus(ids, isDisabled);
            if (!OutEnum.SUCCESS.getCode().equals(code)) {
                baseVO.error(OutEnum.FAIL.getMessage());
                return baseVO;
            }
            baseVO.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }


}
