package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.BlacklistDao;
import org.fh.general.ecom.basics.model.Blacklist;
import org.fh.general.ecom.basics.service.BlacklistService;
import org.fh.general.ecom.common.dto.basics.sms.blacklist.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 黑名单 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-20
 */
@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistDao, Blacklist> implements BlacklistService {

    @Override
   public String addEntity (BlacklistInsertInputDTO dto){
        Blacklist entity=new Blacklist();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
   public String updateEntity (BlacklistUpdateInputDTO dto){
        Blacklist entity=new Blacklist();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override
   public String updateStatus (BlacklistUpdateStatusInputDTO dto){
        if("0".equals(dto.getStatus())){//启用
            baseMapper.updateZeroStatus(dto.getIds());
        }else{//禁用
            baseMapper.updateOneStatus(dto.getIds());
        }
        return OutEnum.SUCCESS.getCode();
    }
    @Override
   public boolean getBlackByParam (BlacklistVerInputDTO dto){
        Blacklist entity=new Blacklist();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(new Date());
        Blacklist blacklist = baseMapper.selectOne(entity);
        if(blacklist!=null){
            return true;
        }
        return false;
    }
    @Override
    public BlacklistListOutputDTO findPage(BlacklistListInputDTO dto)throws Exception {
        BlacklistListOutputDTO response=new BlacklistListOutputDTO();
        EntityWrapper<Blacklist> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
         if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }
        if(StringUtils.isNotEmpty(dto.getStatus())){
            wrapper.eq("status",dto.getStatus());
        }
        if(StringUtils.isNotEmpty(dto.getPhone())){
            wrapper.eq("phone",dto.getPhone());
        }
        if(StringUtils.isNotEmpty(dto.getTimeStart()) && StringUtils.isNotEmpty(dto.getTimeEnd())){
            wrapper.between("create_time",dto.getTimeStart(),dto.getTimeEnd());
        }
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<Blacklist> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<BlacklistOutputDTO>  listpo=new ArrayList<BlacklistOutputDTO>();
        list.forEach((Blacklist temp) -> {
            BlacklistOutputDTO po=new BlacklistOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }

}
