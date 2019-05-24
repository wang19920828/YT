package org.fh.general.ecom.basics.controller;

import org.fh.general.ecom.basics.service.MessageService;
import org.fh.general.ecom.common.base.BaseVO;
import org.fh.general.ecom.common.base.PagingVO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author wzy
 * @since 2018-10-29
 */
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    /**
     * 添加系统消息
     */
    @RequestMapping("ME000001")
    public BaseVO insertMessage (MessageInsertInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=messageService.insertMessage(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 根据id查看
     */
    @RequestMapping("ME000002")
    public BaseVO findMessagebyId (Long id){
        BaseVO baseVO = new BaseVO();
        MessageOutputDTO out=messageService.findMessagebyId(id);
        if(out==null){
            baseVO.noData();
        }else{
            baseVO.success(out);
        }
        return baseVO;
    }
    /**
     *根据id修改
     */
    @RequestMapping("ME000003")
    public BaseVO updateMessagebyId (MessageUpdateInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=messageService.updateMessagebyId(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     *根据id删除
     */
    @RequestMapping("ME000004")
    public BaseVO deleteMessagebyId (Long id){
        BaseVO baseVO = new BaseVO();
        String code=messageService.deleteMessagebyId(id);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     *站内信发布
     */
    @RequestMapping("ME000005")
    public BaseVO publishMessagebyId (MessagePublishInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=messageService.publishMessagebyId(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 站内信  取消发布
     */
    @RequestMapping("ME000006")
    public BaseVO publishNotMessagebyId (MessagePublishInputDTO dto){
        BaseVO baseVO = new BaseVO();
        String code=messageService.publishNotMessagebyId(dto);
        if(!OutEnum.SUCCESS.getCode().equals(code)){
            baseVO.error(code);
            return  baseVO;
        }
        baseVO.success();
        return baseVO;
    }
    /**
     * 分页查询站内信
     */
    @RequestMapping("ME000007")
    public PagingVO findPage(MessageListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        MessageListOutputDTO dtoEntity= this.messageService.findPage(dto);
        List<MessageOutputDTO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(list,dtoEntity.getPageInfo());
        return  pagingVO;
    }

    /**
     * 分页查询系统消息
     */
    @RequestMapping("ME000008")
    public PagingVO findXtPage(MessageListInputDTO dto){
        PagingVO pagingVO=new PagingVO();
        if(StringUtils.isEmpty(dto.getCurrentPageNum()) ||StringUtils.isEmpty(dto.getPageCount()) ){
            pagingVO.mustParam();
            return pagingVO;
        }
        MessageListOutputDTO dtoEntity= this.messageService.findXtPage(dto);
        List<MessageOutputDTO> list= dtoEntity.getList();
        if(list==null || list.size()==0){
            pagingVO.noData();
            return pagingVO;
        }
        pagingVO.success(list,dtoEntity.getPageInfo());
        return  pagingVO;
    }
	
}
