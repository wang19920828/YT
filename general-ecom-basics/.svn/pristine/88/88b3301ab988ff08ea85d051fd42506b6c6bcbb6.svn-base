package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.BlacklistService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.sms.blacklist.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 黑名单 前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-09-20
 */
@RestController
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;
    /**
     * 添加黑名单
     */
    @RequestMapping("BLACK0001")
    public BaseVO addEntity (BlacklistInsertInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=blacklistService.addEntity(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改黑名单
     */
    @RequestMapping("BLACK0002")
    public BaseVO updateEntity (BlacklistUpdateInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=blacklistService.updateEntity(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }

    /**
     * 判断是否是黑名单
     */
    @RequestMapping("BLACK0003")
    public BaseVO getBlackByParam (BlacklistVerInputDTO dto){
        BaseVO baseVO = new BaseVO();
        boolean bo=blacklistService.getBlackByParam(dto);
        if(bo){
            baseVO.success("true");//是启用的黑名单
        }else{
            baseVO.success("false");//没查到
        }
        return baseVO;
    }
    /**
     * 分页查询黑名单
     */
    @RequestMapping("BLACK0004")
    public PagingVO findPage (BlacklistListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        try {

            BlacklistListOutputDTO dtoEntity= this.blacklistService.findPage(dto);
            List<BlacklistOutputDTO> list= dtoEntity.getList();
            if(list==null || list.size()==0){
                pagingVO.noData();
                return pagingVO;
            }
           /* List<UserListVO> listvo=new ArrayList<UserListVO>();
            list.forEach((UserOutputDTO temp) -> {
                UserListVO voEn=new UserListVO();
                BeanUtils.copyProperties(temp,voEn);
                listvo.add(voEn);
            });*/
            pagingVO.success(list,dtoEntity.getPageInfo() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  pagingVO;
    }

    /**
     * 启用禁用黑名单
     */
    public BaseVO updateStatus(BlacklistUpdateStatusInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=blacklistService.updateStatus(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
	
}
