package org.fh.general.ecom.basics.dao;

import org.apache.ibatis.annotations.Mapper;
import org.fh.general.ecom.basics.model.EmailSendInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2018-09-25
 */
@Mapper
public interface EmailSendInfoDao extends BaseMapper<EmailSendInfo> {

}