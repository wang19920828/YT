package org.fh.general.ecom.order.dao;

import org.fh.general.ecom.common.po.order.redProject.GetAmountLeijiInputPO;
import org.fh.general.ecom.order.model.RedProject;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * <p>
  * 分红项目管理表 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface RedProjectDao extends BaseMapper<RedProject> {

    public BigDecimal getAmountLeiji(GetAmountLeijiInputPO paramPO);

}