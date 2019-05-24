package org.fh.general.ecom.basics.service;

import com.baomidou.mybatisplus.service.IService;
import org.fh.general.ecom.basics.model.User;
import org.fh.general.ecom.common.dto.basics.user.*;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wzy
 * @since 2018-09-13
 */
public interface UserService extends IService<User> {
    /**
     * 用户登陆
     */
    public UserLoginOutPutDTO loginUser(UserLoginDTO dto);
    public UserListOutputDTO findPage(UserListInputDTO dto)throws Exception;
    public UserLoginOutPutDTO addEntity(UserAddInputDTO dto)throws Exception;
    public  boolean checkPhoneIsExist(UserAddInputDTO dto)throws Exception;
    public String  addLoginPwd (UserSetPwdInputDTO dto)throws Exception;
    public UserOutputDTO findOne (Long userId)throws Exception;
    public String  updateLoginPwd (UserUpdatePwdInputDTO dto)throws Exception;
    public String deleteEntityById(Long userId)throws Exception;
    public String updateUser(UserUpdateInputDTO dto)throws Exception;
    public String updatePhone(UserUpdatePhoneInputDTO dto)throws Exception;
    public String updateUserPhone(UserOutputDTO dto)throws Exception;
    public  UserOutputDTO attentWeiXin(UserAttentWeiXinInputDTO dto)throws Exception;
    public  UserOutputDTO thirdLogin(UserThirdLoginInputDTO dto)throws Exception;
    public  String logoutUser(Long userId)throws Exception;
    User selectUserOne(User user);
    public String updateUserReal(UserUpdateRealInputDTO dto)throws Exception;
    public String updateUserIsName(UserUpdateIsNameInputDTO dto)throws Exception;
    public UserPhoneOutputDTO findPhoneOne (Long userId)throws Exception;
    Boolean findIsNameByUserId(Long userId);
}
