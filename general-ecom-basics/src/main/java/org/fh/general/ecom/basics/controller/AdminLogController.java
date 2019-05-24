package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.AdminLogService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLog.AdminLogListOutputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminLog.AdminLogListOutputPO;
import org.fh.general.ecom.common.po.basics.adminLoginLog.AdminLoginLogListOutputPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.adminLog.AdminLogListVO;
import org.fh.general.ecom.common.vo.basics.adminLoginLog.AdminLoginLogListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管理员操作日志 前端控制器
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@RestController
public class AdminLogController {

    @Autowired
    private AdminLogService adminLogService;

    /**
     * 分页列表
     */
    @RequestMapping("ADMIN301")
    public PagingVO findPage(AdminLogListInputDTO dto) {
        PagingVO pagingVO = new PagingVO();
        try {
            if (StringUtils.isEmpty(dto.getCurrentPageNum()) || StringUtils.isEmpty(dto.getPageCount())) {
                pagingVO.mustParam();
                return pagingVO;
            }
            AdminLogListOutputDTO dtoEntity = this.adminLogService.findPage(dto);
            List<AdminLogListOutputPO> list = dtoEntity.getList();
            if (list == null || list.size() == 0) {
                pagingVO.noData();
                return pagingVO;
            }
            List<AdminLogListVO> listvo = new ArrayList<AdminLogListVO>();
            list.forEach((AdminLogListOutputPO temp) -> {
                AdminLogListVO voEn = new AdminLogListVO();
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
     * 添加操作日志
     */
    @RequestMapping("ADMIN302")
    public BaseVO addEntity(AdminLogAddInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminLogService.addEntity(dto);
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
