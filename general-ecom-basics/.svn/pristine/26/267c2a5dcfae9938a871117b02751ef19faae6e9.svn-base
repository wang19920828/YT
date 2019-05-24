package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserAddressDao;
import org.fh.general.ecom.basics.model.UserAddress;
import org.fh.general.ecom.basics.model.UserMessage;
import org.fh.general.ecom.basics.service.UserAddressService;
import org.fh.general.ecom.common.dto.basics.user.*;
import org.fh.general.ecom.common.dto.basics.user.userMessage.UserMessageOutputDTO;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-14
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressDao, UserAddress> implements UserAddressService {
    @Autowired
    private UserAddressDao userAddressDao;

    /**
     * 删除
     * @param dto
     * @return
     */
    @Override
    public boolean deleteByPrimaryKey(UserAddressDTO dto){
        long id=dto.getId();
        UserAddress userAddress = userAddressDao.selectByPrimaryKey(id);
        userAddress.setStatus("1");
        int i = userAddressDao.updateByPrimaryKeySelective(userAddress);
       // int i = userAddressDao.deleteByPrimaryKey(id);
        if(i>0){
            return true;
        }else{ return false; }
    }
    /**
     * 查询用户地址集合
     * @param dto
     * @return
     */
    @Override
    public UserAddressListOutPutDTO userAddressList(UserAddressListDto dto){
        long userId=dto.getUserId();
        UserAddressListOutPutDTO out=new UserAddressListOutPutDTO();
        List<UserAddress> list = userAddressDao.selectByUserId(userId);
       List<UserAddressOutPutDTO>  userAddressOutList= new ArrayList<>();
            list.forEach((UserAddress userAddress) ->{
                UserAddressOutPutDTO po=new UserAddressOutPutDTO();
                po.setId(userAddress.getId());
                po.setUserId(userAddress.getUserId());
                po.setIsHost(userAddress.getIsHost());
                po.setName(userAddress.getName());
                po.setSex(userAddress.getSex());
                po.setMobile(userAddress.getMobile());
                po.setAddress(userAddress.getAddress());
                po.setDistrictCode(userAddress.getDistrictCode());
                po.setAreaCode(userAddress.getAreaCode());
                userAddressOutList.add(po);
            });
        out.setUserAddressOutList(userAddressOutList);
        return out;
    }
    /**
     * 根据userId查默认地址
     * @param dto
     */
    @Override
    public UserAddressOutPutDTO selectUserAddressByHost(UserAddressListDto dto) {
        long userId=dto.getUserId();
        UserAddressOutPutDTO po=new UserAddressOutPutDTO();
        UserAddress userAddress = userAddressDao.selectUserAddressByHost(userId);
        if(userAddress!=null){
            po.setId(userAddress.getId());
            po.setUserId(userAddress.getUserId());
            po.setIsHost(userAddress.getIsHost());
            po.setName(userAddress.getName());
            po.setSex(userAddress.getSex());
            po.setMobile(userAddress.getMobile());
            po.setAddress(userAddress.getAddress());
            po.setDistrictCode(userAddress.getDistrictCode());
            po.setAreaCode(userAddress.getAreaCode());
        }
        return po;
    }

    /**
     * 根据Id查用户地址
     * @param dto
     * @return
     */
    @Override
    public UserAddressOutPutDTO selectByPrimaryKey (UserAddressDTO dto){
        long id=dto.getId();
        UserAddress userAddress = userAddressDao.selectByPrimaryKey(id);
        UserAddressOutPutDTO po=new UserAddressOutPutDTO();
        if(userAddress!=null){
            po.setId(userAddress.getId());
            po.setUserId(userAddress.getUserId());
            po.setIsHost(userAddress.getIsHost());
            po.setName(userAddress.getName());
            po.setSex(userAddress.getSex());
            po.setMobile(userAddress.getMobile());
            po.setAddress(userAddress.getAddress());
            po.setDistrictCode(userAddress.getDistrictCode());
            po.setAreaCode(userAddress.getAreaCode());
            po.setStatus(userAddress.getStatus());
    }
        return po;
    }

    /**
     * 添加地址
     * @param dto
     */
    @Override
    public boolean insertAddress (UserAddressInsertDTO dto){
        UserAddress userAddress=new UserAddress();
        userAddress.setUserId(dto.getUserId());
        userAddress.setIsHost(dto.getIsHost());
        userAddress.setSex(dto.getSex());
        userAddress.setName(dto.getName());
        userAddress.setMobile(dto.getMobile());
        userAddress.setAddress(dto.getAddress());
        userAddress.setDistrictCode(dto.getDistrictCode());
        userAddress.setAreaCode(dto.getAreaCode());
        //第一次添加设置为默认地址
        List<UserAddress> list = userAddressDao.selectByUserId(dto.getUserId());
        if(0==list.size()){
            userAddress.setIsHost("1");//1默认，0否
        }else{//不是第一次添加
            if("1".equals(dto.getIsHost())){//且地址为默认地址
                UserAddress address = userAddressDao.selectUserAddressByHost(dto.getUserId());
                address.setIsHost("0");
                userAddressDao.updateByPrimaryKeySelective(address);
            }
        }
        int i = userAddressDao.insertAddress(userAddress);

        if(i>0){
            return true;
        }else{ return false; }

    }
    /**
     * 修改地址
     * @param dto
     */
    @Override
    public boolean updateByPrimaryKeySelective(UserAddressOutPutDTO dto){
        UserAddress userAddress=new UserAddress();
        userAddress.setId(dto.getId());
        userAddress.setUserId(dto.getUserId());
        userAddress.setIsHost(dto.getIsHost());
        userAddress.setName(dto.getName());
        userAddress.setSex(dto.getSex());
        userAddress.setMobile(dto.getMobile());
        userAddress.setAddress(dto.getAddress());
        userAddress.setDistrictCode(dto.getDistrictCode());
        userAddress.setAreaCode(dto.getAreaCode());
        int i = userAddressDao.updateByPrimaryKeySelective(userAddress);
        if(i>0){
            return true;
        }else{ return false; }
    }
    /**
     * 设置默认地址
     */
    @Override
    public boolean setIsHost(UserAddressSetDTO dto){
        UserAddress po=new UserAddress();
        po.setId(dto.getId());
        po.setUserId(dto.getUserId());
        po.setIsHost("1");
        //根据用户查默认地址
        UserAddress userAddress = userAddressDao.selectUserAddressByHost(dto.getUserId());
        //将以往默认修改为0
        if(userAddress!=null){
            userAddress.setIsHost("0");
            int i = userAddressDao.setIsHost(userAddress);
        }
        //根据id修改地址为默认1
        int j = userAddressDao.setIsHost(po);
        if(j>0){
            return true;
        }else{ return false; }

    }
    @Override
    public UserAddressListOutPutDTO findPage (UserAddressListInputDTO dto){
        UserAddressListOutPutDTO output=new UserAddressListOutPutDTO();
            EntityWrapper<UserAddress> wrapper = new EntityWrapper<>();
            PageHelper.startPage(dto.getCurrentPageNum(), dto.getPageCount());
            if(StringUtils.isNotEmpty(dto.getIsHost())){
                wrapper.eq("is_host",dto.getIsHost());
            }
            if(StringUtils.isNotEmpty(dto.getUserId())){
                wrapper.eq("user_id",dto.getUserId());
            }
            if(StringUtils.isNotEmpty(dto.getId())){
                wrapper.eq("id",dto.getId());
            }
            if(StringUtils.isNotEmpty(dto.getName())){
                wrapper.eq("name",dto.getName());
            }
            if(StringUtils.isNotEmpty(dto.getMobile())){
                wrapper.eq("mobile",dto.getMobile());
            }
            if(StringUtils.isNotEmpty(dto.getSex())){
                wrapper.eq("sex",dto.getSex());
            }
                wrapper.eq("status","0");

            System.out.println("================where条件:"+wrapper.getSqlSegment());
            List<UserAddress> list=baseMapper.selectList(wrapper);
            PageInfo pageInfo=new PageInfo(list);
            List<UserAddressOutPutDTO>  listpo=new ArrayList<>();
            list.forEach((UserAddress temp) -> {
                UserAddressOutPutDTO po=new UserAddressOutPutDTO();
                BeanUtils.copyProperties(temp,po);
                listpo.add(po);
            });
            output.setUserAddressOutList(listpo);
            output.setPageInfo(pageInfo);
            return output;
    }
}
