package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.UserAccinfoService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 账户信息日志表
 * </p>
 * @author wzy
 * @since 2018-09-18
 */
@RestController
public class UserAccinfoController {

    @Autowired
    private UserAccinfoService userAccinfoService;
    /**
     * 根据id查
     */
    @RequestMapping("UA000001")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        UserAccinfoOutputDTO out=userAccinfoService.selectByPrimaryKey(id);
        if(out!=null){
            baseVO.success(out);
        }else{
            baseVO.noData();
        }
        return baseVO;
    }

    /**
     * 添加
     * @param dto
     */
    @RequestMapping("UA000005")
    public BaseVO insertUserAccinfo (UserAccinfoInsertInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=userAccinfoService.insertUserAccinfo(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 根据用户ID查询积分记录分页
     */
    public PagingVO findCreditsPage(UserAccinfoListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        UserAccinfoListOutputDTO dtoEntity= this.userAccinfoService.findCreditsPage(dto);
        List<UserAccinfoOutputDTO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(list,dtoEntity.getPageInfo());
        return  pagingVO;
    }
    /**
     * 根据用户ID查询账户记录分页
     */
    public PagingVO findAmountPage(UserAccinfoListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        UserAccinfoListOutputDTO dtoEntity= this.userAccinfoService.findAmountPage(dto);
        List<UserAccinfoOutputDTO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(list,dtoEntity.getPageInfo());
        return  pagingVO;
    }
}
