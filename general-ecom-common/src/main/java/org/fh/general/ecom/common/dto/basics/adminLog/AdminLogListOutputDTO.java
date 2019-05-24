package org.fh.general.ecom.common.dto.basics.adminLog;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.basics.adminLog.AdminLogListOutputPO;

import java.util.List;

@Data
public class AdminLogListOutputDTO {

    private List<AdminLogListOutputPO> list;

    PageInfo pageInfo;
}
