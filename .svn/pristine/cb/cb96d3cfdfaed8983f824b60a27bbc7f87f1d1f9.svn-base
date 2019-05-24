package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserMessageDao;
import org.fh.general.ecom.basics.model.UserMessage;
import org.fh.general.ecom.basics.service.UserMessageService;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 用户站内信息 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageDao, UserMessage> implements UserMessageService {
    @Override
    public String deleteByPrimaryKey(Long id){
        UserMessage entity=new UserMessage();
        entity.setId(id);
        entity.setDel("1");
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public UserMessageOutputDTO selectByPrimaryKey(Long id){
        UserMessageOutputDTO out= new UserMessageOutputDTO();
        UserMessage card = baseMapper.selectById(id);
        if(card!=null){
            BeanUtils.copyProperties(card,out);
        }
        return out;
    }
    @Override
    public String insertUserMessage(UserMessageInsertInputDTO dto){
        String[] arc = dto.getUserIds().split(",");
        Arrays.stream(arc).forEach((String temp)->{
            UserMessage entity=new UserMessage();
            BeanUtils.copyProperties(dto,entity);
            entity.setCreateTime(new Date());
            entity.setUserId(Long.valueOf(temp));
            baseMapper.insert(entity);
        });
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public String updateUserMessage(String ids){
        String[] arc = ids.split(",");
        Arrays.stream(arc).forEach((String temp)->{
            UserMessage entity=new UserMessage();
            entity.setId(Long.valueOf(temp));
            entity.setStatus("1");
            baseMapper.updateById(entity);
        });
        return OutEnum.SUCCESS.getCode();
    }
    @Override
     public UserMessageListOutputDTO findPage(UserMessageListInputDTO dto){
        UserMessageListOutputDTO output=new UserMessageListOutputDTO();
        EntityWrapper<UserMessage> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        if(StringUtils.isNotEmpty(dto.getDel())){
            wrapper.eq("del",dto.getDel());
        }
        if(StringUtils.isNotEmpty(dto.getType())){
            wrapper.eq("type",dto.getType());
        }
        if(StringUtils.isNotEmpty(dto.getUserId())){
            wrapper.eq("user_id",dto.getUserId());
        }
        if(StringUtils.isNotEmpty(dto.getTitle())){
            wrapper.like("title",dto.getTitle());
        }
        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("create_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<UserMessage> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<UserMessageOutputDTO>  listpo=new ArrayList<>();
        list.forEach((UserMessage temp) -> {
            UserMessageOutputDTO po=new UserMessageOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        output.setList(listpo);
        output.setPageInfo(pageInfo);
        return output;
    }

}
