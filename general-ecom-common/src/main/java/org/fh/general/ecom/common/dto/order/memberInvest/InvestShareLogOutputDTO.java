package org.fh.general.ecom.common.dto.order.memberInvest;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.order.OrderListOutPO;
import org.fh.general.ecom.common.vo.order.memberInvest.MemberInvestLogVO;

import java.util.List;

@Data
public class InvestShareLogOutputDTO {

    List<MemberInvestLogVO> logList;

    PageInfo pageInfo;
}
