package org.fh.general.ecom.basics.dao;

import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.Admin;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 管理员 Mapper 接口
 * </p>
 *
 * @author wyk
 * @since 2018-09-19
 */
@Mapper
public interface AdminDao extends BaseMapper<Admin> {

    Admin findByName(String name);

    Admin findByPhone(String adminPhone);

    Admin findByEmail(String adminEmail);

    int updateBatchAdmin(Map<String, Object> param);

    List<Admin> findAdminList(Map<String,Object> map);

    Admin selectByPrimaryKey(Long adminId);

    int updateByPrimaryKey(Admin admin);



}