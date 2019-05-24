package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserAccinfoDao;
import org.fh.general.ecom.basics.model.User;
import org.fh.general.ecom.basics.model.UserAccinfo;
import org.fh.general.ecom.basics.service.DictionaryService;
import org.fh.general.ecom.basics.service.UserAccinfoService;
import org.fh.general.ecom.basics.service.UserMessageService;
import org.fh.general.ecom.basics.service.UserService;
import org.fh.general.ecom.common.dto.basics.dictionary.InputDictionaryQueryDTO;
import org.fh.general.ecom.common.dto.basics.dictionary.OutputDictionaryDetailDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageInsertInputDTO;
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
 * 积分表 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@Service
public class UserAccinfoServiceImpl extends ServiceImpl<UserAccinfoDao, UserAccinfo> implements UserAccinfoService {
    @Autowired
   private UserService userService;
    @Autowired
   private DictionaryService dictionaryService;
    @Autowired
    private UserMessageService userMessageService;

    @Override
    public UserAccinfoOutputDTO selectByPrimaryKey(Long id){
        UserAccinfoOutputDTO out= new UserAccinfoOutputDTO();
        UserAccinfo card = baseMapper.selectById(id);
        if(card!=null){
            BeanUtils.copyProperties(card,out);
            return out;
        }
        return null;
    }
    @Override
    public String insertUserAccinfo(UserAccinfoInsertInputDTO dto){
        //根据用户的id查询到用户，计算用户的总积分，保存流水，保存用户积分
        User user = userService.selectById(dto.getUserId());
        if(user==null){
            return "未查到用户！";
        }
        Long allCredits=user.getCredits()+dto.getCredits();
        UserAccinfo entity=new UserAccinfo();
        BeanUtils.copyProperties(dto,entity);
        entity.setAllCredits(allCredits);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
        user.setCredits(allCredits);
        userService.updateById(user);
        //积分消息通知
        String str="您的积分入账+"+dto.getCredits()+"，点击查看明细。";
        addUserMessage(dto.getBranch(),dto.getUserId(),str,"积分变动通知","2");
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public String insertMinusUserAccinfo(UserAccinfoInsertInputDTO dto){
        //根据用户的id查询到用户，计算用户的总积分，保存流水，保存用户积分
        User user = userService.selectById(dto.getUserId());
        if(user==null){
            return "未查到用户！";
        }
        if(user.getCredits()<dto.getCredits()){
            return "用户积分数量不足！";
        }else{
            dto.setCredits((0L-dto.getCredits()));
        }
        Long allCredits=user.getCredits()+dto.getCredits();
        UserAccinfo entity=new UserAccinfo();
        BeanUtils.copyProperties(dto,entity);
        entity.setAllCredits(allCredits);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
        user.setCredits(allCredits);
        userService.updateById(user);
        //积分消息通知
        String str="您的消费积分-"+dto.getCredits()+"，点击查看明细。";
        addUserMessage(dto.getBranch(),dto.getUserId(),str,"积分变动通知","2");
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public UserAccinfoListOutputDTO findCreditsPage(UserAccinfoListInputDTO dto){
        UserAccinfoListOutputDTO output=new UserAccinfoListOutputDTO();
        EntityWrapper<UserAccinfo> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        if(StringUtils.isNotEmpty(dto.getUserId())){
            wrapper.eq("user_id",dto.getUserId());
        }
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }
        if(StringUtils.isNotEmpty(dto.getType())){
            wrapper.eq("type",dto.getType());
        }
        if(StringUtils.isNotEmpty(dto.getOrderOn())){
            wrapper.eq("order_on",dto.getOrderOn());
        }
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<UserAccinfo> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<UserAccinfoOutputDTO>  listpo=new ArrayList<>();
        list.forEach((UserAccinfo temp) -> {
            UserAccinfoOutputDTO po=new UserAccinfoOutputDTO();
            BeanUtils.copyProperties(temp,po);
            InputDictionaryQueryDTO dic=new InputDictionaryQueryDTO();
            dic.setValue(temp.getType());
            dic.setType("integral_type");
            OutputDictionaryDetailDTO label = dictionaryService.findLabelByValueAndType(dic);
            if(null!=label){
                po.setTypeText(label.getLabel());
            }
            listpo.add(po);
        });
        output.setList(listpo);
        output.setPageInfo(pageInfo);
        return output;
    }
    @Override
    public UserAccinfoListOutputDTO findAmountPage(UserAccinfoListInputDTO dto){
        UserAccinfoListOutputDTO output=new UserAccinfoListOutputDTO();
        EntityWrapper<UserAccinfo> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
      /*  wrapper.eq("user_id",dto.getUserId());//用户id
        wrapper.isNotNull("amount");
        wrapper.notIn("amount","0.00");*/
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<UserAccinfo> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<UserAccinfoOutputDTO>  listpo=new ArrayList<>();
        list.forEach((UserAccinfo temp) -> {
            UserAccinfoOutputDTO po=new UserAccinfoOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        output.setList(listpo);
        output.setPageInfo(pageInfo);
        return output;
    }
    /**
     * 积分消息通知
     */
    private void addUserMessage(String branch,Long userId, String message, String title,String corr){
        UserMessageInsertInputDTO mm=new UserMessageInsertInputDTO();
        mm.setUserIds(userId.toString());
        mm.setBranch(branch);
        mm.setTitle(title);
        mm.setMessage(message);
        mm.setCorr(corr);
        mm.setType("3");
        //账户消息通知
        userMessageService.insertUserMessage(mm);
    }

}
