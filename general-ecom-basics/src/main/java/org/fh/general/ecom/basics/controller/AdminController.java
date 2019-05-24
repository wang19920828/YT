package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.AdminService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.admin.*;
import org.fh.general.ecom.common.enumeration.basics.AdminEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.admin.AdminListOutPO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.fh.general.ecom.common.vo.basics.admin.AdminDetailVO;
import org.fh.general.ecom.common.vo.basics.admin.AdminListVO;
import org.fh.general.ecom.common.vo.basics.admin.AdminLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登陆
     */
    @RequestMapping("ADMIN101")
    public BaseVO loginAdmin(AdminLoginDTO dto) {
        BaseVO baseVO = new BaseVO();
        AdminLoginOutPutDTO out = adminService.loginAdmin(dto);
        if (!AdminEnum.AdminLogin.SUCCESS.getValue().equals(out.getCode())) {
            baseVO.exception(AdminEnum.AdminLogin.codeOf(out.getCode()).getName());
            return baseVO;
        }
        AdminLoginVO vo=new AdminLoginVO();
        BeanUtils.copyProperties(out,vo);
        baseVO.success(vo);
        return baseVO;
    }

    /**
     * 重置管理员密码
     * */
    @RequestMapping("ADMIN102")
    public BaseVO resetPwd(Long adminId){
        BaseVO baseVO=new BaseVO();
        String code=adminService.resetPwd(adminId);
        if (!OutEnum.SUCCESS.getCode().equals(code)) {
            baseVO.error(OutEnum.FAIL.getMessage());
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     *修改密码
     * */
    @RequestMapping("ADMIN103")
    public BaseVO updatePwd(AdminUpdatePwdDTO dto){
        BaseVO baseVO=new BaseVO();
        String code=adminService.updatePwd(dto);
        if(!AdminEnum.AdminUpdatePwd.SUCCESS.getValue().equals(code)){
            baseVO.warn(AdminEnum.AdminUpdatePwd.codeOf(code).getName());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 退出登录
     */
    @RequestMapping("ADMIN104")
    public BaseVO loginOff(String browserCode){
        BaseVO baseVO=new BaseVO();
        String code=adminService.loginOff(browserCode);
        if (!OutEnum.SUCCESS.getCode().equals(code)) {
            baseVO.error(OutEnum.FAIL.getMessage());
            return baseVO;
        }
        baseVO.success();
        return baseVO;
    }



    /**
     * 管理员分页
     */
    @RequestMapping("ADMIN001")
    public PagingVO findPage(AdminListInputDTO dto) {
        PagingVO pagingVO = new PagingVO();
        try {
            if (StringUtils.isEmpty(dto.getCurrentPageNum()) || StringUtils.isEmpty(dto.getPageCount())) {
                pagingVO.mustParam();
                return pagingVO;
            }
            AdminListOutPutDTO out = this.adminService.findPage(dto);
            List<AdminListOutPO> list = out.getList();
            if (list == null || list.size() == 0) {
                pagingVO.noData();
                return pagingVO;
            }
            List<AdminListVO> listvo = new ArrayList<>();
            list.forEach((AdminListOutPO temp) -> {
                AdminListVO voEn = new AdminListVO();
                BeanUtils.copyProperties(temp, voEn);
                listvo.add(voEn);
            });
            pagingVO.success(listvo, out.getPageInfo());
        } catch (Exception e) {
            e.printStackTrace();
            pagingVO.exception(e.getMessage());
        }
        return pagingVO;
    }

    /**
     * 添加管理员
     */
    @RequestMapping("ADMIN002")
    public BaseVO addEntity(AdminAddInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminService.addEntity(dto);
            if (!AdminEnum.AdminAdd.SUCCESS.getValue().equals(code)) {
                baseVO.error(AdminEnum.AdminAdd.codeOf(code).getName());
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
    @RequestMapping("ADMIN003")
    public BaseVO deleteEntityById(Long id) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminService.deleteEntityById(id);
            if (!OutEnum.SUCCESS.getCode().equals(code)) {
                baseVO.exception(OutEnum.FAIL.getMessage());
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
     * 修改
     */
    @RequestMapping("ADMIN004")
    public BaseVO updateEntity(AdminUpdateInputDTO dto) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminService.updateEntity(dto);
            if(!AdminEnum.AdminAdd.SUCCESS.getValue().equals(code)){
                baseVO.exception(AdminEnum.AdminAdd.codeOf(code).getName());
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
     * 详情
     */
    @RequestMapping("ADMIN005")
    public BaseVO findEntityById(Long adminId) {
        BaseVO baseVO = new BaseVO();
        try {
            AdminDetailOutputDTO response = this.adminService.findEntityById(adminId);
            if (response == null) {
                baseVO.noData();
                return baseVO;
            }
            AdminDetailVO vo = new AdminDetailVO();
            BeanUtils.copyProperties(response, vo);
            baseVO.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseVO;
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("ADMIN006")
    public BaseVO updateStatus(String ids, String status) {
        BaseVO baseVO = new BaseVO();
        try {
            String code = this.adminService.updateStatus(ids, status);
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
     * 根据角色id查询用户列表
     */
    @RequestMapping("ADMIN007")
    public BaseVO findList(String roleId){
        BaseVO baseVO=new BaseVO();
        AdminListOutPutDTO out = this.adminService.findListByRoleId(roleId);
        List<AdminListOutPO> list = out.getList();
        List<AdminListVO> listvo = new ArrayList<>();
        list.forEach((AdminListOutPO temp) -> {
            AdminListVO voEn = new AdminListVO();
            BeanUtils.copyProperties(temp, voEn);
            listvo.add(voEn);
        });
        baseVO.setData(listvo);
        baseVO.success();
        return baseVO;
    }


    @RequestMapping("RADMIN005")
    public AdminDetailOutputDTO findAdminEntityById(@RequestBody  Long adminId) {
        AdminDetailOutputDTO response = null;
        try {
            response = this.adminService.findEntityById(adminId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }


}
