package org.fh.general.ecom.basics.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.fh.general.ecom.basics.dao.UserDao;
import org.fh.general.ecom.basics.model.Customer;
import org.fh.general.ecom.basics.model.User;
import org.fh.general.ecom.basics.service.CustomerService;
import org.fh.general.ecom.basics.service.UserService;
import org.fh.general.ecom.common.dto.basics.user.*;
import org.fh.general.ecom.common.enums.OutEnum;
import org.fh.general.ecom.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wzy
 * @since 2018-09-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private CustomerService customerService;
    @Override
    public UserLoginOutPutDTO loginUser(UserLoginDTO dto) {
        User user1=new  User ();
        BeanUtils.copyProperties(dto,user1 );
        user1.setChannel(null);
        user1.setStatus("1");
        UserLoginOutPutDTO out=new UserLoginOutPutDTO();
        try {
           //根据用户loginName查询用户
            //User user=userDao.findByLoginName(loginName);
            User user= baseMapper.selectOne(user1);
            if(user==null){
                out.setCode(OutEnum.WARN.getCode());
                return out;
            }
            //修改用户上次登陆时间
            user.setLoginTime(new Date());
            baseMapper.updateById(user);
            BeanUtils.copyProperties(user,out );
            out.setCode(OutEnum.SUCCESS.getCode());
            return out;
        } catch (Exception e) {
            out.setCode(OutEnum.FAIL.getCode());
            e.printStackTrace();
        }
        return out;
    }
    @Override
    public UserListOutputDTO findPage(UserListInputDTO dto)throws Exception {
        UserListOutputDTO response=new UserListOutputDTO();
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        PageHelper.startPage(dto.getCurrentPageNum(),dto.getPageCount() );
        /* if(StringUtils.isNotEmpty(dto.getOrderSn())){
            wrapper.eq("order_sn",dto.getOrderSn());
        }
        if(StringUtils.isNotEmpty(dto.getBranch())){
            wrapper.eq("branch",dto.getBranch());
        }*/
        wrapper.eq("status","1");//正常状态
        wrapper.eq("branch",dto.getBranch());//平台
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<User> list=baseMapper.selectList(wrapper);

        PageInfo pageInfo=new PageInfo(list);
        List<UserOutputDTO>  listpo=new ArrayList<UserOutputDTO>();
        list.forEach((User temp) -> {
            UserOutputDTO po=new UserOutputDTO();
            BeanUtils.copyProperties(temp,po);
            listpo.add(po);
        });

        response.setList(listpo);
        response.setPageInfo(pageInfo);
        return response;
    }

    @Override
    public UserLoginOutPutDTO addEntity(UserAddInputDTO dto)  throws Exception{
        UserLoginOutPutDTO out= new UserLoginOutPutDTO();
        try {
            User entity=new User();
            BeanUtils.copyProperties(dto,entity );
            entity.setRegisterTime(new Date());
            entity.setLoginTime(new Date());
            entity.setPhone(dto.getLoginName());
            //entity.setUpdateTime(new Date());
            entity.setAccount(createaccount());
            baseMapper.insert(entity);//添加
            //添加基本信息
            Customer customer= new Customer();
            customer.setUserId(entity.getUserId());
            customerService.insert(customer);
           //查询
            //User us = baseMapper.selectOne(entity);
           // BeanUtils.copyProperties(entity,out );
            out.setCode(OutEnum.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            out.setCode(OutEnum.FAIL.getCode());
        }
        return out;
    }
	
    @Override
    public  boolean checkPhoneIsExist(UserAddInputDTO dto) throws Exception{
        try {
            User user1=new User();
            user1.setLoginName(dto.getLoginName());
            user1.setBranch(dto.getBranch());
            user1.setStatus("1");
            User us = baseMapper.selectOne(user1);
            if(us==null){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String addLoginPwd(UserSetPwdInputDTO dto) throws Exception{
        User user=new User();
        BeanUtils.copyProperties(dto,user);
        baseMapper.updateById(user);
        return  OutEnum.SUCCESS.getCode();
    }

    @Override
    public UserOutputDTO findOne(Long userId) throws Exception{
        UserOutputDTO out=new UserOutputDTO();
        User user = baseMapper.selectById(userId);
        if(user!=null){
            BeanUtils.copyProperties(user,out);
        }
        return out;
    }

    @Override
    public String updateLoginPwd(UserUpdatePwdInputDTO dto) throws Exception{
        User user=new User();
        user.setUserId(dto.getUserId());
        user.setLoginPwd(dto.getNewLoginPwd());
        user.setUpdateTime(new Date());
        baseMapper.updateById(user);
        return  OutEnum.SUCCESS.getCode();
    }
    @Override
    public String updateUser(UserUpdateInputDTO dto)throws Exception{
        User user=new User();
        BeanUtils.copyProperties(dto,user);
        user.setUpdateTime(new Date());
        baseMapper.updateById(user);
        return  OutEnum.SUCCESS.getCode();
    }
    @Override
    public String updateUserPhone(UserOutputDTO dto)throws Exception{
        User user=new User();
        BeanUtils.copyProperties(dto,user);
        user.setUpdateTime(new Date());
        baseMapper.updateById(user);
        return  OutEnum.SUCCESS.getCode();
    }
    @Override
    public String deleteEntityById(Long userId)throws Exception{
        User user=new User();
        user.setUserId(userId);
        user.setStatus("3");
        user.setUpdateTime(new Date());
        baseMapper.updateById(user);
        return  OutEnum.SUCCESS.getCode();
    }
    @Override
    public String updatePhone(UserUpdatePhoneInputDTO dto)throws Exception{
        User user=new User();
        BeanUtils.copyProperties(dto,user);
        user.setUpdateTime(new Date());
        user.setPhone(dto.getNewPhone());
        user.setLoginName(dto.getNewPhone());
        baseMapper.updateById(user);
        return  OutEnum.SUCCESS.getCode();
    }
    @Override
    public User selectUserOne(User user){
        return  baseMapper.selectOne(user);
    }

    @Override
    public  UserOutputDTO attentWeiXin(UserAttentWeiXinInputDTO dto)throws Exception{
        UserOutputDTO out=new UserOutputDTO();
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("unionid",dto.getUnionid());
        wrapper.eq("status","1");
        wrapper.eq("branch",dto.getBranch());
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<User> list=baseMapper.selectList(wrapper);
        if(list.size()>0){
            BeanUtils.copyProperties(list.get(0),out);
        }else{//新建用户
            User us=new User();
            BeanUtils.copyProperties(dto,us);
            us.setRealUnionid(dto.getUnionid());
            us.setRegisterTime(new Date());
            us.setAccount(createaccount());
            baseMapper.insert(us); //添加会员
            //添加基本信息
            Customer customer= new Customer();
            customer.setUserId(us.getUserId());
            customerService.insert(customer);

            List<User> li=baseMapper.selectList(wrapper);
            BeanUtils.copyProperties(li.get(0),out);
        }
        return  out;
    }
    @Override
    public  UserOutputDTO thirdLogin(UserThirdLoginInputDTO dto)throws Exception{
        UserOutputDTO out=new UserOutputDTO();
        EntityWrapper<User> wrapper = new EntityWrapper<>();
         if(StringUtils.isNotEmpty(dto.getUnionid())){
             wrapper.eq("unionid",dto.getUnionid());
         }
         if(StringUtils.isNotEmpty(dto.getWeibouid())){
             wrapper.eq("weibouid",dto.getWeibouid());
         }
         if(StringUtils.isNotEmpty(dto.getPcqquid())){
             wrapper.eq("pcqquid",dto.getPcqquid());
         }
         if(StringUtils.isNotEmpty(dto.getQquid())){
             wrapper.eq("qquid",dto.getQquid());
         }
        wrapper.eq("status","1");
        wrapper.eq("branch",dto.getBranch());
        System.out.println("================where条件:"+wrapper.getSqlSegment());
        List<User> userList = baseMapper.selectList(wrapper);
        if(userList.size()>0){
            BeanUtils.copyProperties(userList.get(0),out);
        }else{
            User user=new User();
            BeanUtils.copyProperties(dto,user);
            user.setRealUnionid(dto.getUnionid());
            user.setRegisterTime(new Date());
            user.setAccount(createaccount());
            baseMapper.insert(user);//添加会员
            //添加基本信息Customer
            Customer customer= new Customer();
            customer.setUserId(user.getUserId());
            customerService.insert(customer);

            List<User> li=baseMapper.selectList(wrapper);
            BeanUtils.copyProperties(li.get(0),out);
        }
        return  out;
    }
    /**
     * 注销用户
     */
    public  String logoutUser(Long userId)throws Exception{
        User user =new User();
        user.setUserId(userId);
        user.setStatus("2");
        user.setUpdateTime(new Date());
        baseMapper.updateById(user);
       //注销Customer
        Customer customer=new Customer();
        CustomerOutputDTO customerOut = customerService.findEntityByUserId(userId);
        customer.setCustId(customerOut.getCustId());
        customer.setCustStatus("3");//逻辑删除
        customerService.updateById(customer);
        return OutEnum.SUCCESS.getCode();
    }

    /**
     * 账号生成
     */
    public String createaccount(){
        long str = new Date().getTime();
        Random rand= new Random();
        int i = rand.nextInt(9999);
        if (i< 1000) {
            i += 1000;
        }
        return "ZH"+str+i;
    }


}
