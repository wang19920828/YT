package org.fh.general.ecom.common.dto.order.memberInvest;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.vo.order.calendar.CalendarListVO;
import org.fh.general.ecom.common.vo.order.memberInvest.MemberInvestListVO;
import org.fh.general.ecom.common.vo.order.memberInvest.MemberInvestLogVO;
import org.fh.general.ecom.common.vo.order.memberInvest.MemberPlanListVO;

import java.util.List;

@Data
public class MemberDetailPageOutputDTO {

    //  会员信息
    private String userName;
    private  String phone;
    private String projectName;
    private String projectStatus;
    private String projectId;

    //项目详情
    List<MemberPlanListVO> planList;

}
