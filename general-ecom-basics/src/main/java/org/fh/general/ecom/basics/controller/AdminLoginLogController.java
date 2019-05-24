package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.AdminLoginLogService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogAddInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListInputDTO;
import org.fh.general.ecom.common.dto.basics.adminLoginLog.AdminLoginLogListOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminLoginLog.AdminLoginLogListOutputPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.adminLoginLog.AdminLoginLogListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管理员登录日志 前端控制器
 * </p>
 *
 * @author wyk
 * @since 2018-09-20
 */
@RestController
public class AdminLoginLogController {

    @Autowired
    private AdminLoginLogService adminLoginLogService;

    /**
     * 分页列表
     */
    @RequestMapping("ADMIN201")
    public PagingVO findPage(AdminLoginLogListInputDTO dto) {
        PagingVO pagingVO = new PagingVO();
        try {
            if (StringUtils.isEmpty(dto.getCurrentPageNum()) || StringUtils.isEmpty(dto.getPageCount())) {
                pagingVO.mustParam();
                return pagingVO;
            }
            AdminLoginLogListOutputDTO dtoEntity = this.adminLoginLogService.findPage(dto);
            List<AdminLoginLogListOutputPO> list = dtoEntity.getList();
            if (list == null || list.size() == 0) {
                pagingVO.noData();
                return pagingVO;
            }
            List<AdminLoginLogListVO> listvo = new ArrayList<AdminLoginLogListVO>();
            list.forEach((AdminLoginLogListOutputPO temp) -> {
                AdminLoginLogListVO voEn = new AdminLoginLogListVO();
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
     * 添加登录日志
     */
    @RequestMapping("ADMIN202")
    public BaseVO addEntity(AdminLoginLogAddInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminLoginLogService.addEntity(dto);
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
