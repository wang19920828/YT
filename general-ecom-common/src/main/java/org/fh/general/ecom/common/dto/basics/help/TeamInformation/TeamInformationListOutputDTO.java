package org.fh.general.ecom.common.dto.basics.help.TeamInformation;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.basics.help.TeamInformationListOutPO;

import java.util.List;
@Data
public class TeamInformationListOutputDTO {
    private List<TeamInformationListOutPO> list;

    PageInfo pageInfo;
}
