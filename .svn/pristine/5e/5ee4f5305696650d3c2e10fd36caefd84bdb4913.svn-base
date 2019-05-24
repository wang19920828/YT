package org.fh.general.ecom.basics.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.Email;

import java.util.List;

/**
 * <p>
  * 邮件 Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
@Mapper
public interface EmailDao extends BaseMapper<Email> {
    List<Email> findAllEmail(Email email);
    public Email selectByEmailChannel(String emailChannel);
}