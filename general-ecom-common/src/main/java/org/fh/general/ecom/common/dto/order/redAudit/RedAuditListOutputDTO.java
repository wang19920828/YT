package org.fh.general.ecom.common.dto.order.redAudit;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.redAudit.RedAuditListOutPO;

import java.util.List;

@Data
public class RedAuditListOutputDTO {

    private List<RedAuditListOutPO> list;

    PageInfo pageInfo;

}
