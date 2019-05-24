package org.fh.general.ecom.order.dao;

import org.fh.general.ecom.common.po.order.memberInvest.MemberDetailOutPO;
import org.fh.general.ecom.common.po.order.memberInvest.MemberInvestListParamPO;
import org.fh.general.ecom.common.po.order.memberInvest.MemberInvestListOutPO;
import org.fh.general.ecom.common.po.order.memberInvest.MemberPlanListOutPO;
import org.fh.general.ecom.common.po.order.memberInvest.MemberPlanListParamPO;
import org.fh.general.ecom.order.model.MemberInvest;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 会员投资表（一个会员多次购买同一个项目的不同方案）；以项目为单位 Mapper 接口
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface MemberInvestDao extends BaseMapper<MemberInvest> {

    List<MemberInvestListOutPO> findMemberInvestList(MemberInvestListParamPO paramPO);

    List<MemberPlanListOutPO> findMemberPlanList(MemberPlanListParamPO paramPO);

    MemberDetailOutPO memberDetail(String userId);
}