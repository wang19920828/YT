package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.fh.general.ecom.basics.dao.UserGuidePlayDao;
import org.fh.general.ecom.basics.model.UserGuidePlay;
import org.fh.general.ecom.basics.service.UserGuidePlayService;
import org.fh.general.ecom.common.dto.basics.help.UserGuidePlay.*;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-10-22
 */
@Service
public class UserGuidePlayServiceImpl extends ServiceImpl<UserGuidePlayDao, UserGuidePlay> implements UserGuidePlayService {

    @Override
    public UserGuidePlayOutputDTO selectByPrimaryKey(Long id) {
        UserGuidePlayOutputDTO entity2=new UserGuidePlayOutputDTO();
        UserGuidePlay entity=baseMapper.selectById(id);
        BeanUtils.copyProperties(entity,entity2);
        return entity2;
    }

    @Override
    public String insertUserGuidePlay(UserGuidePlayInputDTO dto) {
        UserGuidePlay entity =new UserGuidePlay();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String updateUserGuidePlay(UserGuidePlayInputDTO dto) {
        UserGuidePlay entity =new UserGuidePlay();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String deleteByPrimaryKey(long l) {
        UserGuidePlay entity=baseMapper.selectById(l);
        entity.setDel(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }
    @Override

    public UserGuidePlayListOutputDTO findPage(UserGuidePlayListInputDTO dto) {
        UserGuidePlayListOutputDTO response=new UserGuidePlayListOutputDTO();
        EntityWrapper<UserGuidePlay> wrapper = new EntityWrapper<>();
        wrapper.eq("del",ComEnum.IsDelete.NORMAL.getValue());
        List<UserGuidePlay> list=baseMapper.selectList(wrapper);
        List<UserGuidePlayListOutPo>  listpo=new ArrayList<UserGuidePlayListOutPo>();
        list.forEach((UserGuidePlay temp) -> {
            UserGuidePlayListOutPo po=new UserGuidePlayListOutPo();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
            response.setList(listpo);
            return response;
    }
}
