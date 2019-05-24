package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserMessageDao;
import org.fh.general.ecom.basics.model.User;
import org.fh.general.ecom.basics.model.UserMessage;
import org.fh.general.ecom.basics.service.MessageService;
import org.fh.general.ecom.basics.service.UserMessageService;
import org.fh.general.ecom.basics.service.UserService;
import org.fh.general.ecom.common.dto.basics.user.userMessage.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
   @Autowired
   private UserService userService;
   @Autowired
   private MessageService messageService;
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

        if(StringUtils.isEmpty(dto.getUserIds())){//userIds不传时发给说有用户
            EntityWrapper<User> wrapper = new EntityWrapper<>();
            wrapper.eq("branch",dto.getBranch());
            wrapper.eq("status","1");
            List<User> users = userService.selectList(wrapper);
            List<String> list=new ArrayList<>();
            users.forEach((User u)->{
                list.add(u.getUserId().toString());
            });
            if(list.size()>0){
                UserMessageInsertInputPO po=new UserMessageInsertInputPO();
                BeanUtils.copyProperties(dto,po);
                po.setCreateTime(new Date());
                po.setUserIds(list);
                //添加到后台消息统计,推送1个人，2部分，3全部
                Long pid = addMessage(po, "3");
                if(null==pid){
                    return "添加系统消息失败！";
                }
                po.setPid(pid);
                baseMapper.insertByIds(po);
            }
        }else{
            String[] arc= dto.getUserIds().split(",");
            List<String> list1= Arrays.asList(arc);//将数组转化为集合
            UserMessageInsertInputPO po=new UserMessageInsertInputPO();
            BeanUtils.copyProperties(dto,po);
            po.setCreateTime(new Date());
            po.setUserIds(list1);
            //添加到后台消息统计,推送1个人，2部分，3全部
            Long pid;
            if(list1.size()>1){
                pid = addMessage(po, "2");
            }else{
                pid = addMessage(po, "1");
            }
            if(null==pid){
                return "添加系统消息失败！";
            }
            po.setPid(pid);
            baseMapper.insertByIds(po);
        }
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
            wrapper.eq("del","0");

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
        wrapper.orderBy("id",false);
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
    @Override
    public UserMessageAllOutputDTO findAllMessage(UserMessageAllInputDTO dto){
        UserMessageAllOutputDTO out=new UserMessageAllOutputDTO();
        //站内信
        List<UserMessage> mlist = findMessageEntity(dto.getUserId(), "0", "1", dto.getBranch());
        if(mlist.size()>0){
            out.setMStatus("1");
            out.setMTime(mlist.get(0).getCreateTime());
        }else{
            out.setMStatus("0");
            out.setMTime(null);
        }out.setMContent("您有"+mlist.size()+"条系统消息");
        //项目
        List<UserMessage> plist = findMessageEntity(dto.getUserId(), "0", "2", dto.getBranch());
        if(plist.size()>0){
            out.setPStatus("1");
            out.setPTime(plist.get(0).getCreateTime());
            out.setPContent(plist.get(0).getTitle());
        }else{
            out.setPStatus("0");
            out.setPTime(null);
        }
        //账户
        List<UserMessage> elist = findMessageEntity(dto.getUserId(), "0", "3", dto.getBranch());
        if(elist.size()>0){
            out.setEStatus("1");
            out.setETime(elist.get(0).getCreateTime());
            out.setEContent(elist.get(0).getTitle());
        }else{
            out.setEStatus("0");
            out.setETime(null);
        }
        return out;
    }
    @Override
    public  String updateUserMessageTypeStatus (UserMessageUpdateInputDTO dto){
        baseMapper.updateUserMessageTypeStatus(dto);
        return OutEnum.SUCCESS.getCode();
    }

    private List<UserMessage> findMessageEntity (Long userId ,String status, String type,String branch){

        EntityWrapper<UserMessage> wrapper = new EntityWrapper<>();
        wrapper.eq("branch",branch);
        wrapper.eq("user_id",userId);
        wrapper.eq("status",status);
        wrapper.eq("type",type);
        wrapper.eq("del","0");
        wrapper.orderBy("id",false);
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<UserMessage> list=baseMapper.selectList(wrapper);
        return list;
    }
    public Long addMessage(UserMessageInsertInputPO po,String pushType){
        MessageInsertItInputDTO dto =new MessageInsertItInputDTO();
        dto.setPushType(pushType);
        //类型1，站内，2项目，3账户，4积分，5优惠
        dto.setType(po.getType());
        if("3".equals(po.getType())){
            if("2".equals(po.getCorr())){
                dto.setType("4");
            }
            if("3".equals(po.getCorr())){
                dto.setType("5");
            }
        }
        dto.setBranch(po.getBranch());
        dto.setContent(po.getMessage());
        dto.setTitle(po.getTitle());
        if(StringUtils.isNotEmpty(po.getImg())){
            dto.setImg(po.getImg());
        }
        if(StringUtils.isNotEmpty(po.getFlag())){
            dto.setFlag(po.getFlag());
        }
        Long s = messageService.insertItMessage(dto);
        return s;
    }

}
