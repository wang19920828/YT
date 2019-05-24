package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserGuideDao;
import org.fh.general.ecom.basics.model.UserGuide;
import org.fh.general.ecom.basics.service.UserGuideService;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideListInputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideListOutputDTO;
import org.fh.general.ecom.common.dto.basics.help.UserGuide.UserGuideOutputDTO;
import org.fh.general.ecom.common.enums.ComEnum;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.po.basics.help.UserGuideListOutPo;
import org.fh.general.ecom.common.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyy
 * @since 2018-09-21
 */
@Service
public class UserGuideServiceImpl extends ServiceImpl<UserGuideDao, UserGuide> implements UserGuideService {

    @Override
    public String updateUserGuide(UserGuideInputDTO dto) {
        UserGuide entity =new UserGuide();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public UserGuideOutputDTO selectByPrimaryKey(Long id) {
        UserGuideOutputDTO entity2=new UserGuideOutputDTO();
        UserGuide entity=baseMapper.selectById(id);
        BeanUtils.copyProperties(entity,entity2);
        return entity2;
    }

    @Override
    public String insertUserGuide(UserGuideInputDTO dto) {
        UserGuide entity =new UserGuide();
        BeanUtils.copyProperties(dto,entity);
        baseMapper.insert(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public String deleteByPrimaryKey(Long id) {
        UserGuide entity=baseMapper.selectById(id);
        entity.setDel(ComEnum.IsDelete.DEL.getValue());
        baseMapper.updateById(entity);
        return OutEnum.SUCCESS.getCode();
    }

    @Override
    public UserGuideListOutputDTO findPage(UserGuideListInputDTO dto) {
        UserGuideListOutputDTO response=new UserGuideListOutputDTO();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        EntityWrapper<UserGuide> wrapper = new EntityWrapper<>();
        Date d1=DateUtils.getDate(dto.getStartTime(),DateUtils.DATE_FROMAT1);
        Date d2=DateUtils.getDate(dto.getEndTime(),DateUtils.DATE_FROMAT1);
        wrapper.between("publish_time",d1,d2);
        List<UserGuide> list=baseMapper.selectList(wrapper);
        PageInfo pageInfo=new PageInfo(list);
        List<UserGuideListOutPo>  listpo=new ArrayList<UserGuideListOutPo>();
        list.forEach((UserGuide temp) -> {
            UserGuideListOutPo po=new UserGuideListOutPo();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });
        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }
}
