package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.AdminDao;
import org.fh.general.ecom.basics.dao.MessageDao;
import org.fh.general.ecom.basics.dao.UserMessageDao;
import org.fh.general.ecom.basics.model.Admin;
import org.fh.general.ecom.basics.model.Message;
import org.fh.general.ecom.basics.model.User;
import org.fh.general.ecom.basics.service.MessageService;
import org.fh.general.ecom.basics.service.UserService;
import org.fh.general.ecom.common.dto.basics.user.userMessage.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-10-29
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMessageDao userMessageDao;
    /**
     * 添加系统消息
     */
    @Override
   public String insertMessage(MessageInsertInputDTO dto){
        String code="";
        Message po=new Message();
        BeanUtils.copyProperties(dto,po);
        Admin admin = adminDao.selectByPrimaryKey(dto.getAdminId());
       if(admin!=null){
           po.setOpName(admin.getRealName());//操作人
       }
        po.setOpTime(new Date());//操作时间
        po.setCreateTime(new Date());//创建时间
        baseMapper.insert(po);
        return OutEnum.SUCCESS.getCode();
    }
    /**
     *记录其他消息，返回id
     */
    @Override
    public Long insertItMessage(MessageInsertItInputDTO dto){
        Message po=new Message();
        BeanUtils.copyProperties(dto,po);
        po.setPushTime(new Date());//推送时间
        po.setCreateTime(new Date());//创建时间
        po.setOpTime(new Date());//操作时间
        po.setOpName("系统推送");
        po.setStatus("1");//发布
        Integer i = baseMapper.insert(po);
        if(i>0){
            return po.getId();
        }
        return null;
    }
    /**
     * 根据id查询
     */
    @Override
    public MessageOutputDTO findMessagebyId(Long id){
        MessageOutputDTO out=new MessageOutputDTO();
        Message message = baseMapper.selectById(id);
        if(message!=null){
            BeanUtils.copyProperties(message,out);
            return out;
        }
        return null;
    }
    /**
     *根据id修改
     */
    @Override
    public String updateMessagebyId(MessageUpdateInputDTO dto){
        String code="";
        Message po=baseMapper.selectById(dto.getId());
        Admin admin = adminDao.selectByPrimaryKey(dto.getAdminId());
        if(admin!=null){
            po.setOpName(admin.getRealName());//操作人
        }
        po.setOpTime(new Date());//操作时间
        po.setTitle(dto.getTitle());
        po.setContent(dto.getContent());
        po.setFlag(dto.getFlag());
        po.setStandby(dto.getStandby());
        baseMapper.updateById(po);
        return OutEnum.SUCCESS.getCode();
    }
    /**
     *根据id删除
     */
    @Override
    public String deleteMessagebyId(Long id){
        Message po= new Message ();
        po.setId(id);
        po.setDel("1");
        baseMapper.updateById(po);
        return OutEnum.SUCCESS.getCode();
    }
    /**
     *根据id发布
     */
    @Override
    public String publishMessagebyId(MessagePublishInputDTO dto){
        //修改此条信息
        Message message = baseMapper.selectById(dto.getId());
        message.setPushType("3");//全部
        message.setPushTime(new Date());
        Admin admin = adminDao.selectByPrimaryKey(dto.getAdminId());
        if(admin!=null){
            message.setOpName(admin.getRealName());//操作人
        }
        message.setOpTime(new Date());//操作时间
        message.setStatus("1");//发布
        baseMapper.updateById(message);
        //发布给全部会员
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
            po.setBranch(dto.getBranch());
            po.setMessage(message.getContent());
            po.setTitle(message.getTitle());
            po.setPid(message.getId());
            po.setType("1");
            po.setCreateTime(new Date());
            po.setUserIds(list);
            userMessageDao.insertByIds(po);
        }
        return OutEnum.SUCCESS.getCode();
    }
    /**
     *根据id 取消发布
     */
    @Override
    public String publishNotMessagebyId(MessagePublishInputDTO dto){
        //修改此条信息
        Message message = baseMapper.selectById(dto.getId());
        Admin admin = adminDao.selectByPrimaryKey(dto.getAdminId());
        if(admin!=null){
            message.setOpName(admin.getRealName());//操作人
        }
        message.setOpTime(new Date());//操作时间
        message.setStatus("2");//发布取消
        baseMapper.updateById(message);
        //将发布给全部会员的信息给逻辑删除了
        UserMessageUpdateByPidInputPO um=new UserMessageUpdateByPidInputPO();
        um.setPid(dto.getId());
        um.setBranch(dto.getBranch());
        userMessageDao.updateByPid(um);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public MessageListOutputDTO findPage(MessageListInputDTO dto){
        MessageListOutputDTO output=new MessageListOutputDTO();
        EntityWrapper<Message> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        wrapper.eq("del","0");
        wrapper.eq("type","1");

        if(StringUtils.isNotEmpty(dto.getTitle())){
            wrapper.like("title",dto.getTitle());
        }
        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("op_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        wrapper.orderBy("id",false);
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<Message> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<MessageOutputDTO>  listpo=new ArrayList<>();
        list.forEach((Message temp) -> {
            MessageOutputDTO po=new MessageOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        output.setList(listpo);
        output.setPageInfo(pageInfo);
        return output;
    }
    @Override
    public MessageListOutputDTO findXtPage(MessageListInputDTO dto){
        MessageListOutputDTO output=new MessageListOutputDTO();
        EntityWrapper<Message> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }
        wrapper.eq("status","1");
        wrapper.eq("del","0");
        if(StringUtils.isNotEmpty(dto.getTitle())){
            wrapper.like("title",dto.getTitle());
        }
        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("push_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        wrapper.orderBy("id",false);
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<Message> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<MessageOutputDTO>  listpo=new ArrayList<>();
        list.forEach((Message temp) -> {
            MessageOutputDTO po=new MessageOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        output.setList(listpo);
        output.setPageInfo(pageInfo);
        return output;
    }

}
