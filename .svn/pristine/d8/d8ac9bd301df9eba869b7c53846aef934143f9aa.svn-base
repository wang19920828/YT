package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserAccinfoDao;
import org.fh.general.ecom.basics.model.UserAccinfo;
import org.fh.general.ecom.basics.service.UserAccinfoService;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoInsertInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListInputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoListOutputDTO;
import org.fh.general.ecom.common.dto.basics.user.userAccinfo.UserAccinfoOutputDTO;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 账户信息日志表 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-18
 */
@Service
public class UserAccinfoServiceImpl extends ServiceImpl<UserAccinfoDao, UserAccinfo> implements UserAccinfoService {

    @Override
    public UserAccinfoOutputDTO selectByPrimaryKey(Long id){
        UserAccinfoOutputDTO out= new UserAccinfoOutputDTO();
        UserAccinfo card = baseMapper.selectById(id);
        if(card!=null){
            BeanUtils.copyProperties(card,out);
        }
        return out;
    }
    @Override
    public String insertUserAccinfo(UserAccinfoInsertInputDTO dto){
        UserAccinfo entity=new UserAccinfo();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
    public UserAccinfoListOutputDTO findCreditsPage(UserAccinfoListInputDTO dto){
        UserAccinfoListOutputDTO output=new UserAccinfoListOutputDTO();
        EntityWrapper<UserAccinfo> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        wrapper.eq("user_id",dto.getUserId());//用户id
        wrapper.isNotNull("credits");
        wrapper.notIn("credits","0");
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
    @Override
    public UserAccinfoListOutputDTO findAmountPage(UserAccinfoListInputDTO dto){
        UserAccinfoListOutputDTO output=new UserAccinfoListOutputDTO();
        EntityWrapper<UserAccinfo> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
        wrapper.eq("user_id",dto.getUserId());//用户id
        wrapper.isNotNull("amount");
        wrapper.notIn("amount","0.00");
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

}
