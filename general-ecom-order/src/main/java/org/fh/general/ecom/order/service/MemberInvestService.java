package org.fh.general.ecom.order.service;

import org.fh.general.ecom.common.dto.order.memberInvest.InvestShareLogInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.InvestShareLogOutputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberDetailPageInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberDetailPageOutputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberInvestAddInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberInvestListInputDTO;
import org.fh.general.ecom.common.dto.order.memberInvest.MemberInvestListOutputDTO;
import org.fh.general.ecom.order.model.MemberInvest;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 会员投资表（一个会员多次购买同一个项目的不同方案）；以项目为单位 服务类
 * </p>
 *
 * @author pjj
 * @since 2018-09-14
 */
public interface MemberInvestService extends IService<MemberInvest> {




    MemberInvestListOutputDTO findPage(MemberInvestListInputDTO dto)throws Exception;


    String addEntity(MemberInvestAddInputDTO dto)throws Exception;

    MemberDetailPageOutputDTO detailBgMemberInvest(MemberDetailPageInputDTO paramDto);

    InvestShareLogOutputDTO detailInvestShareLogPage(InvestShareLogInputDTO dto);

}
