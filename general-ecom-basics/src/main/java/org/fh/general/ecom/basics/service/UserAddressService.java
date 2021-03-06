package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.UserAddress;
import org.fh.general.ecom.common.dto.basics.user.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-14
 */
public interface UserAddressService extends IService<UserAddress> {
   public boolean deleteByPrimaryKey(UserAddressDTO dto);

   public boolean insertAddress (UserAddressInsertDTO dto);

   UserAddressOutPutDTO selectByPrimaryKey (UserAddressDTO dto);

    public boolean updateByPrimaryKeySelective(UserAddressOutPutDTO dto);
    UserAddressListOutPutDTO userAddressList(UserAddressListDto dto);
    UserAddressOutPutDTO  selectUserAddressByHost(UserAddressListDto dto);
    public boolean setIsHost(UserAddressSetDTO dto);

    public UserAddressListOutPutDTO findPage (UserAddressListInputDTO dto);

	
}
