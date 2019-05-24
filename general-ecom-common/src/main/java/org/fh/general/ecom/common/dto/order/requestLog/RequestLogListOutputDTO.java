package org.fh.general.ecom.common.dto.order.requestLog;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.fh.general.ecom.common.po.order.requestLog.RequestLogListOutPO;

import java.util.List;

@Data
public class RequestLogListOutputDTO {


    private List<RequestLogListOutPO> list;

    PageInfo pageInfo;


}
