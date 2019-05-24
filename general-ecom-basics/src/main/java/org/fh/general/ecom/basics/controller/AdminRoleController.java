package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.AdminFunctionService;
import org.fh.general.ecom.basics.service.AdminRoleFunService;
import org.fh.general.ecom.basics.service.AdminRoleService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.adminRole.ShouquanInitOutputDTO;
import org.fh.general.ecom.common.dto.basics.adminRole.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.adminRole.RoleListOutputPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.adminrole.ShouquanInitVO;
import org.fh.general.ecom.common.vo.basics.adminrole.RoleDetailVO;
import org.fh.general.ecom.common.vo.basics.adminrole.RoleListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
@RestController
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminFunctionService adminFunctionService;

    @Autowired
    private AdminRoleFunService adminRoleFunService;

    /**
     * 分页列表
     */
    @RequestMapping("ROLE0001")
    public PagingVO findPage(RoleListInputDTO dto) {
        PagingVO pagingVO = new PagingVO();
        try {
            if (StringUtils.isEmpty(dto.getCurrentPageNum()) || StringUtils.isEmpty(dto.getPageCount())) {
                pagingVO.mustParam();
                return pagingVO;
            }
            RoleListOutputDTO dtoEntity = this.adminRoleService.findPage(dto);
            List<RoleListOutputPO> list = dtoEntity.getList();
            if (list == null || list.size() == 0) {
                pagingVO.noData();
                return pagingVO;
            }
            List<RoleListVO> listvo = new ArrayList<RoleListVO>();
            list.forEach((RoleListOutputPO temp) -> {
                RoleListVO voEn = new RoleListVO();
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
    @RequestMapping("ROLE0002")
    public BaseVO addEntity(RoleAddInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            if(this.adminRoleService.selectByName(dto.getBranch(),dto.getRoleName())){
                baseVO.exception("角色名称已存在！");
                return baseVO;
            }
            String code = this.adminRoleService.addEntity(dto);
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
     * 删除
     */
    @RequestMapping("ROLE0003")
    public BaseVO deleteEntityById(Long id) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminRoleService.deleteEntityById(id);
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
    @RequestMapping("ROLE0004")
    public BaseVO updateEntity(RoleUpdateInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            if (StringUtils.isEmpty(dto.getId())) {
                baseVO.mustParam();
                return baseVO;
            }
            String code = this.adminRoleService.updateEntity(dto);
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
     * 详情
     */
    @RequestMapping("ROLE0005")
    public BaseVO findEntityById(Long id) {
        BaseVO baseVO = new BaseVO();
        try {
            RoleDetailOutputDTO response = this.adminRoleService.findEntityById(id);
            if (response == null) {
                baseVO.noData();
                return baseVO;
            }
            RoleDetailVO vo = new RoleDetailVO();
            BeanUtils.copyProperties(response, vo);
            List<ShouquanInitOutputDTO> shouquandto=this.adminFunctionService.findFuncListByRoleId(id);
            List<ShouquanInitVO> vos = new ArrayList<ShouquanInitVO>();
            shouquandto.forEach((ShouquanInitOutputDTO temp) -> {
                ShouquanInitVO voEn = new ShouquanInitVO();
                BeanUtils.copyProperties(temp, voEn);
                vos.add(voEn);
            });
            vo.setShouquanList(vos);
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("ROLE0006")
    public BaseVO updateStatus(String ids, String isDisabled) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminRoleService.updateStatus(ids, isDisabled);
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
     * 授权初始化
     */
    @RequestMapping("ROLE0007")
    public BaseVO shouquanInit(Long roleId) {
        BaseVO baseVO = new BaseVO();
        try {
            List<ShouquanInitOutputDTO> response=this.adminFunctionService.findFuncListByRoleId(roleId);
            List<ShouquanInitVO> vos = new ArrayList<ShouquanInitVO>();
            response.forEach((ShouquanInitOutputDTO temp) -> {
                ShouquanInitVO voEn = new ShouquanInitVO();
                BeanUtils.copyProperties(temp, voEn);
                vos.add(voEn);
            });
            baseVO.success(vos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 角色授权
     */
    @RequestMapping("ROLE0008")
    public BaseVO saveShouquan(ShouquanSaveInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            String code=this.adminRoleService.saveShouquan(dto);
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

    @RequestMapping("ROLE0009")
    public BaseVO findRoleList(String branch){
        BaseVO baseVO=new BaseVO();
        RoleListOutputDTO out=this.adminRoleService.selectRoleList(branch);
        List<RoleListOutputPO> list = out.getList();
        List<RoleListVO> listvo = new ArrayList<RoleListVO>();
        list.forEach((RoleListOutputPO temp) -> {
            RoleListVO voEn = new RoleListVO();
            BeanUtils.copyProperties(temp, voEn);
            listvo.add(voEn);
        });
        baseVO.setData(listvo);
        baseVO.success();
        return baseVO;
    }




}
