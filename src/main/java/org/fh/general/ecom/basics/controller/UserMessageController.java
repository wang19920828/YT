package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.UserMessageService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户站内信息
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@RestController
public class UserMessageController {
    @Autowired
    private UserMessageService userMessageService;

    /**
     *删除
     */
    @RequestMapping("UMES000001")
    public BaseVO deleteById (Long id){
        BaseVO baseVO=new BaseVO();
        String code=this.userMessageService.deleteByPrimaryKey(id);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return  baseVO;
    }


    /**
     * 根据id查
     */
    @RequestMapping("UMES000003")
    public BaseVO selectByPrimaryKey (Long id){
        BaseVO baseVO = new BaseVO();
        UserMessageOutputDTO out=userMessageService.selectByPrimaryKey(id);
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
    @RequestMapping("UMES000005")
    public BaseVO insertUserMessage (UserMessageInsertInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=userMessageService.insertUserMessage(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 修改状态
     */
    @RequestMapping("UMES000006")
    public BaseVO updateUserMessage (String ids){
        BaseVO baseVO = new BaseVO();
        String code=userMessageService.updateUserMessage(ids);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(OutEnum.FAIL.getMessage());
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 用户信息查询（分页）
     */
    @RequestMapping("UMES000002")
    public PagingVO findPage(UserMessageListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        UserMessageListOutputDTO dtoEntity= this.userMessageService.findPage(dto);
        List<UserMessageOutputDTO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(list,dtoEntity.getPageInfo());
        return  pagingVO;
    }


}
